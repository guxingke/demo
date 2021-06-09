package com.gxk.demo;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 4k       | [0, 0x1000)      | initldrimh.bin 1425
 * 4k       | [0x1000, 0x2000) | fs header
 * 4k       | [0x2000, 0x3000) | files meta
 * 4k align | [0x3000, $]      | files body
 */
public class Main {

  private static final int _4K = 0x1000;


  // ./lmoskrlimg -m k -lhf initldrimh.bin -o Cosmos.eki -f initldrsve.bin initldrkrl.bin Cosmos.bin font.fnt logo.bmp background.bm
  // ->
  // -d release_dir -lhf initldrimh.bin -o Cosmos.eki -f initldrsve.bin initldrkrl.bin Cosmos.bin font.fnt logo.bmp background.bm
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      System.out.println(
          "-d release_dir -lhf initldrimh.bin -o Cosmos.eki -f initldrsve.bin initldrkrl.bin Cosmos.bin font.fnt logo.bmp background.bm");
      return;
    }
    // args
    String dir = "release";
    String init = null;
    String output = null;
    String[] files = null;

    for (int i = 0; i < args.length; i++) {
      final String t = args[i];
      if (t.equalsIgnoreCase("-d")) {
        dir = args[++i];
        continue;
      }
      if (t.equalsIgnoreCase("-lhf")) {
        init = args[++i];
        continue;
      }
      if (t.equalsIgnoreCase("-o")) {
        output = args[++i];
        continue;
      }
      if (t.equalsIgnoreCase("-f")) {
        final int len = args.length - i - 1;
        files = new String[len];

        System.arraycopy(args, i + 1, files, 0, len);
      }
    }

    if (dir == null || init == null || files == null || output == null) {
      System.err.println("check input ...\n");
      return;
    }

    // total size
    int total = _4K * 3;

    for (String file : files) {
      final File f = Paths.get(dir, file).toFile();
      final String name = f.getName();
      final long len = f.length();

      int nl = align(len);
      total += nl;
    }

    // gen fs header
//    00001000: 4c4d 4f53 4d44 534b 6516 0000 0000 0000  LMOSMDSKe.......
//    00001010: 0010 0000 0000 0000 ff1f 0000 0000 0000  ................
//    00001020: b000 0000 0000 0000 0000 0000 0000 0000  ................
//    00001030: ff0f 0000 0000 0000 0010 0000 0000 0000  ................
//    00001040: 0000 0000 0000 0000 0020 0000 0000 0000  ......... ......
//    00001050: ff2f 0000 0000 0000 0006 0000 0000 0000  ./..............
//    00001060: 0000 0000 0000 0000 0030 0000 0000 0000  .........0......
//    00001070: ff5f 4c00 0000 0000 0030 4c00 0000 0000  ._L......0L.....
//    00001080: 0000 0000 0000 0000 0100 0000 0000 0000  ................
//    00001090: 0600 0000 0000 0000 0600 0000 0000 0000  ................
//    000010a0: ffaa ffaa ffaa ffaa aaff aaff aaff aaff  ................

//    typedef struct s_mlosrddsc
//    {
//      u64_t mdc_mgic;
//      u64_t mdc_sfsum;
//      u64_t mdc_sfsoff;
//      u64_t mdc_sfeoff;
//      u64_t mdc_sfrlsz;
//      u64_t mdc_ldrbk_s;
//      u64_t mdc_ldrbk_e;
//      u64_t mdc_ldrbk_rsz;
//      u64_t mdc_ldrbk_sum;
//      u64_t mdc_fhdbk_s; ! 0020 -> 0x2000
//      u64_t mdc_fhdbk_e;
//      u64_t mdc_fhdbk_rsz;
//      u64_t mdc_fhdbk_sum;
//      u64_t mdc_filbk_s;
//      u64_t mdc_filbk_e; ! total
//      u64_t mdc_filbk_rsz;
//      u64_t mdc_filbk_sum;
//      u64_t mdc_ldrcodenr;
//      u64_t mdc_fhdnr;  ! 6
//      u64_t mdc_filnr; ! 6
//      u64_t mdc_endgic; !
//      u64_t mdc_rv; !
//    }mlosrddsc_t;

    final ByteBuffer hb = ByteBuffer.allocate(_4K);
    hb.order(ByteOrder.LITTLE_ENDIAN);
    for (int i = 0; i < 9; i++) {
      hb.putLong(0);
    }
    hb.putLong(0x2000); // mdc_fhdbk_s
    for (int i = 0; i < 4; i++) {
      hb.putLong(0);
    }
    hb.putLong(total);
    for (int i = 0; i < 3; i++) {
      hb.putLong(0);
    }
    hb.putLong(files.length); // mdc_fhdnr
    hb.putLong(files.length); // mdc_fhdnr
    hb.putLong(0xaaffaaffaaffaaffL);
    hb.putLong(0xffaaffaaffaaffaaL);

    final byte[] hbb = hb.array();
    Files.write(Paths.get(dir, "fh"), hbb, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

    // gen files meta
//    00002000: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00002010: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00002020: 0030 0000 0000 0000 ff3f 0000 0000 0000  .0.......?......
//    00002030: b701 0000 0000 0000 5fa7 0000 0000 0000  ........_.......
//    00002040: 696e 6974 6c64 7273 7665 2e62 696e 0000  initldrsve.bin..
//    00002050: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00002060: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00002070: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00002080: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00002090: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000020a0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000020b0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000020c0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000020d0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000020e0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000020f0: 0000 0000 0000 0000 0000 0000 0000 0000  ................

//    00000000: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00000010: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00000020: 0030 0000 0000 0000 0000 0000 0000 0000  .0..............
//    00000030: b701 0000 0000 0000 0000 0000 0000 0000  ................
//    00000040: 696e 6974 6c64 7273 7665 2e62 696e 0000  initldrsve.bin..
//    00000050: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00000060: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00000070: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00000080: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    00000090: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000000a0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000000b0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000000c0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000000d0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000000e0: 0000 0000 0000 0000 0000 0000 0000 0000  ................
//    000000f0: 0000 0000 0000 0000 0000 0000 0000 0000  ................

//    typedef struct s_fhdsc
//    {
//      u64_t fhd_type;
//      u64_t fhd_subtype;
//      u64_t fhd_stuts;
//      u64_t fhd_id;
//      u64_t fhd_intsfsoff; !
//      u64_t fhd_intsfend;
//      u64_t fhd_frealsz; !
//      u64_t fhd_fsum;
//      char   fhd_name[FHDSC_NMAX]; 192 !
//    }fhdsc_t;

    final ByteBuffer mb = ByteBuffer.allocate(_4K);
    hb.order(ByteOrder.LITTLE_ENDIAN);
    int os = _4K * 3;
    for (String file : files) {
      final File f = Paths.get(dir, file).toFile();
      final long len = f.length();
      final char[] chars = f.getName().toCharArray();
      int nl = align(len);

      final ByteBuffer mf = ByteBuffer.allocate(256);
      mf.order(ByteOrder.LITTLE_ENDIAN);
      for (int i = 0; i < 4; i++) {
        mf.putLong(0);
      }
      mf.putLong(os);
      mf.putLong(0);
      mf.putLong(len);
      mf.putLong(0);

      for (char c : chars) {
        mf.put(((byte) c));
      }

      mb.put(mf.array());
      os += nl;
    }

    final byte[] fmb = mb.array();
    Files.write(Paths.get(dir, "fm"), fmb, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

    p("# total size: " + total);

    // gen cmd ----------
    p("cd " + dir);
    p("touch " + output);
    p("dd if=/dev/zero of=" + output + " bs=1 count=" + total + " conv=notrunc");

    p("# init");
    p("dd if=" + init + " of=" + output + " seek=" + _4K * 0 + " bs=1 conv=notrunc");

    p("# fs header");
    p("dd if=" + "fh " + " of=" + output + " seek=" + _4K * 1 + " bs=1 conv=notrunc");

    p("# files meta");
    p("dd if=" + "fm" + " of=" + output + " seek=" + _4K * 2 + " bs=1 conv=notrunc");

    p("# files");

    int offset = _4K * 3;
    for (String file : files) {
      final File f = Paths.get(dir, file).toFile();
      final long len = f.length();
      int nl = align(len);

      p("dd if=" + file + " of=" + output + " seek=" + offset + " bs=1 conv=notrunc");

      offset += nl;
    }
  }

  private static void p(String p) {
    System.out.println(p);
  }

  private static int align(long len) {
    final long x = len / _4K;
    final long y = len % _4K;
    if (y == 0) {
      return ((int) len);
    }
    return (int) ((x + 1) * _4K);
  }
}
