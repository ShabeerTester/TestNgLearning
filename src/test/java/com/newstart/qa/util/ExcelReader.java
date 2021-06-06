package com.newstart.qa.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class handles multiple reading operations of content from Excel workbook
 *
 * @author aadhityan.nagarajan
 */
public class ExcelReader {

    /**
     * path to the spreadsheet
     */
    private String path;

    /**
     * stream to read the spreadsheet
     */
    private FileInputStream fis = null;

    /**
     * access to the spreadsheet
     */
    private XSSFWorkbook workbook = null;

    /**
     * sheet in the current workbook
     */
    private XSSFSheet sheet = null;

    /**
     * row in the current sheet
     */
    private XSSFRow row = null;

    /**
     * cell in the current row
     */
    private XSSFCell cell = null;

    private static final String UNABLE_TO_OPEN = "Unable to open spreadhseet";

    private static final String NULL_PATH = "Unable to open spreadsheet as path is null";

    private static final String FROM_WBOOK = "\" from workbook \"";

    public ExcelReader(final String path) {
        if (path != null) {
            this.setPath(path);
            if (this.getPath() != null) {
                try {
                    setFis(new FileInputStream(this.getPath()));
                    if (this.getFis() != null) {
                        XSSFWorkbook xssfWB = new XSSFWorkbook(getFis());
                        setWorkbook(xssfWB);
                    }
                } catch (IOException ioe) {
                    System.out.println(UNABLE_TO_OPEN);
                } finally {
                    if (this.getFis() != null) {
                        try {
                            getFis().close();
                        } catch (IOException ioe1) {
                            ioe1.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public String getCellValue(final int row, final int col) {
        Row rows = getSheet().getRow(row - 1);
        if (rows == null) {
            System.out.println("attempt to read data from cell in undefined row" + "[" + row + "]" + "," + col + "] on sheet \"" + getSheet().getSheetName() + "\"");
            return null;
        } else {
            Cell cell = null;
            try {
                cell = rows.getCell(col);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
            if (cell == null) {
                return "";
            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date d = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    return sdf.format(d);
                } else {
                    return new java.text.DecimalFormat("0").format(cell.getNumericCellValue());
                }
            } else {
                return cell.getStringCellValue();
            }
        }
    }

    public String getCellValue(final String sheetName, final int row, final int col) {
        try {
            if (row <= 0) {
                return "";
            }

            int index = getWorkbook().getSheetIndex(sheetName);

            if (index == -1) {
                return "";
            }

            setSheet(getWorkbook().getSheet(sheetName));
            if (getSheet() != null) {
                Row rows = getSheet().getRow(row - 1);
                if (rows == null) {
                    return "";
                }
                Cell cell = rows.getCell(col);
                if (cell == null) {
                    return "";
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date d = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        return sdf.format(d);
                    } else {
                        return new java.text.DecimalFormat("0").format(cell.getNumericCellValue());
                    }
                } else {
                    return cell.getStringCellValue();
                }
            }else{
                System.out.println("unable to retrive sheet");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getRowCount() {
        if (getSheet() != null) {
            int rowCount = getSheet().getLastRowNum();
            return rowCount + 1;
        } else {
            System.out.println("sheet not found");
            return 0;
        }
    }

    public int getRowCount(final String sheetName) {
        setSheet(getWorkbook().getSheet(sheetName));
        if (getSheet() != null) {
            int rowCount = getSheet().getLastRowNum();
            return rowCount + 1;
        } else {
            System.out.println("sheet not found");
            return 0;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(final FileInputStream fis) {
        this.fis = fis;
    }

    public XSSFWorkbook getWorkbook() {
        return this.workbook;
    }

    public void setWorkbook(final XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public XSSFSheet getSheet() {
        return this.sheet;
    }

    public void setSheet(final XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public XSSFRow getRow() {
        return this.row;
    }

    public void setRow(final XSSFRow row) {
        this.row = row;
    }

    public XSSFCell getCell() {
        return this.cell;
    }

    public void setCell(final XSSFCell cell) {
        this.cell = cell;
    }
}
