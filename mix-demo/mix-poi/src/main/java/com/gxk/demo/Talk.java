package com.gxk.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class Talk {

  public final List<Row> npcs;
  public final List<Row> users;

  public Talk(List<Row> npcs, List<Row> users) {
    this.npcs = npcs;
    this.users = users;
  }

  public void parse() {
    // parse npcs
    for (Row npc : npcs) {
      Cell cell = npc.getCell(2);
      switch (cell.getCellType()) {
        case STRING:
          String sval = cell.getStringCellValue();
          System.out.println(sval);
          break;
        default:
          throw new IllegalArgumentException();
      }
    }
    System.out.println();

    // parse users
    List<List<Row>> options = new ArrayList<>();
    List<Row> rows = new ArrayList<>();
    int stat = 1; // 2 cont
    for (Row user : users) {
      Cell cell = user.getCell(2);
      CellType type = cell.getCellType();

      if (stat == 1 && type == CellType.STRING) {
        rows.add(user);
        stat = 2;
        continue;
      }

      if (stat == 2) {
        if (type == CellType.BLANK) {
          rows.add(user);
        } else if (type == CellType.STRING) {
          options.add(rows);
          rows = new ArrayList<>();
          rows.add(user);
        }
        continue;
      }

      throw new IllegalArgumentException();
    }
    options.add(rows);

    // user-options
    Map<Integer, List<Row>> optionMap = new LinkedHashMap<>();
    int idx = 1;
    for (List<Row> option : options) {
      stat = 1;
      List<Row> feedbacks = new ArrayList<>();
      for (Row row : option) {
        Cell cell = row.getCell(2);
        if (stat == 1 && cell.getCellType() == CellType.STRING) {
          stat = 2;
          feedbacks.add(row);
          continue;
        }
        if (stat == 2 && cell.getCellType() == CellType.BLANK) {
          feedbacks.add(row);
          continue;
        }
        if (stat == 2 && cell.getCellType() == CellType.STRING) {
          optionMap.put(idx++, feedbacks);
          feedbacks = new ArrayList<>();
          feedbacks.add(row);
          continue;
        }
        throw new IllegalArgumentException();
      }
      optionMap.put(idx++, feedbacks);
    }

    Collection<List<Row>> values = optionMap.values();
    System.out.println(values.size());
    for (List<Row> value : values) {
      if (value.size() == 1) {
        Row row = value.get(0);
        System.out.println(row.getCell(2).getStringCellValue());
        CellType cellType = row.getCell(3).getCellType();
        switch (cellType) {
          case BLANK:
            System.out.println("action: jump to next");
            break;
          case STRING:
            System.out.println("msg: " + row.getCell(3).getStringCellValue());
            break;
          default:
            throw new IllegalArgumentException();
        }
        continue;
      }
      Row main = value.get(0);

      System.out.println(main.getCell(2).getStringCellValue());
      CellType cellType = main.getCell(3).getCellType();
      switch (cellType) {
        case BLANK:
          System.out.println("action: jump to next");
          break;
        case STRING:
          System.out.println("msg: " + main.getCell(3).getStringCellValue());
          break;
        default:
          throw new IllegalArgumentException();
      }
      for (int i = 1; i < value.size(); i++) {
        Row row = value.get(i);
        cellType = row.getCell(3).getCellType();
        switch (cellType) {
          case BLANK:
            System.out.println("action: jump to next");
            break;
          case STRING:
            System.out.println("msg: " + row.getCell(3).getStringCellValue());
            break;
          default:
            throw new IllegalArgumentException();
        }
      }
    }
  }
}
