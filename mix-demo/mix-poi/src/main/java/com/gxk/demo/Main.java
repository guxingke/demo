package com.gxk.demo;

import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

  public static void main(String[] args) throws IOException {
    FileInputStream is = new FileInputStream("/Users/gxk/Downloads/杀猪盘_李子杰.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(is);
    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
      XSSFSheet sheet = workbook.getSheetAt(i);
      System.out.println(sheet.getSheetName());
      System.out.println();

      List<Row> rows = new ArrayList<>();
      Iterator<Row> rowIterator = sheet.rowIterator();
      while (rowIterator.hasNext()) {
        Row next = rowIterator.next();
        rows.add(next);
      }

      List<Rectangular> rectangulars = new ArrayList<>();
      {
        for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
          int firstRow = mergedRegion.getFirstRow();
          int lastRow = mergedRegion.getLastRow();
          int firstColumn = mergedRegion.getFirstColumn();
          int lastColumn = mergedRegion.getLastColumn();
          rectangulars.add(new Rectangular(firstColumn, firstRow, 1, lastRow - firstRow + 1));
        }
      }

      // filter 1
      List<Rectangular> rec0 = rectangulars.stream().filter(it -> it.x == 0)
          .collect(Collectors.toList());
      rectangulars.removeAll(rec0);

      int index = 1;
      Map<Integer, List<Row>> talkMap = new LinkedHashMap<>();
      for (Rectangular rec : rec0) {
        int y = rec.y;
        int h = rec.h;

        talkMap.put(index++, new ArrayList<>(rows.subList(y, y + h)));
      }

      // step1 , 序号校验
      talkMap.forEach((key, val) -> {
        for (Row row : val) {
          Cell cell = row.getCell(0);
          CellType type = cell.getCellType();
          if (type == BLANK) {
            continue;
          }
          if (type != NUMERIC) {
            System.err.println(key + " column0 type err");
            System.exit(2);
          }

          double dval = cell.getNumericCellValue();
          int ival = (int) dval;
          if (key != ival) {
            System.err.println(key + " column0 val err , " + ival);
            System.exit(3);
          }
        }
      });

      // step2 parse one talk
      Map<Integer, Talk> talkMap2 = new LinkedHashMap<>();
      talkMap.forEach((key, val) -> {
        List<Row> npcs = new ArrayList<>();
        List<Row> users = new ArrayList<>();
        int stat = 1;
        for (Row row : val) {
          Cell cell = row.getCell(1);
          CellType type = cell.getCellType();
          // npc
          if (stat == 1) {
            if (type == STRING) {
              npcs.add(row);
              stat = 2;
            } else {
              System.err.println("stat err");
            }
            continue;
          }
          if (stat == 2) {
            if (type == STRING) {
              stat = 3;
              users.add(row);
            } else if (type == BLANK) {
              npcs.add(row);
            }
            continue;
          }
          users.add(row);
        }

        talkMap2.put(key, new Talk(npcs, users));
      });

      for (Talk value : talkMap2.values()) {
        value.parse();
        System.out.println();
        System.out.println();
      }
    }
  }
}
