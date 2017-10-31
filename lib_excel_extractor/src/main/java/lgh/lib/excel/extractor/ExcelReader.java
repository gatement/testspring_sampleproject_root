package lgh.lib.excel.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import lgh.lib.excel.extractor.annotation.ExcelField;
import lgh.lib.excel.extractor.dto.ExcelHeaderField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelReader {
	private static ExcelReader excelReader = new ExcelReader();

	private ExcelReader() {
	}

	public static ExcelReader getInstance() {
		return excelReader;
	}

	public <T> List<T> readExcelAsBean(String path, Class<T> clz) {
		return this.readExcelAsBean(path, clz, 0, 0);
	}

	public <T> List<T> readExcelAsBean(InputStream inputStream, Class<T> clz) {
		return this.readExcelAsBean(inputStream, clz, 0, 0);
	}

	/**
	 * 从指定文件路径读取相应的Excel文件到对象列表
	 *
	 * @param path
	 *            Excel文件路径
	 * @param clz
	 *            对象类型
	 * @param startLine
	 *            开始行(包括标题所在行)
	 * @param tailLine
	 *            忽略的底部行数
	 * @return
	 */
	public <T> List<T> readExcelAsBean(String path, Class<T> clz, int startLine, int tailLine) {
		try {
			Workbook workbook = WorkbookFactory.create(new File(path));
			return readExcelAsBean(workbook, clz, startLine, tailLine);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			log.warn(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			log.warn(e.toString());
		}
		return null;
	}

	/**
	 * 从文件流读取相应的Excel到对象列表
	 *
	 * @param inputStream
	 *            Excel文件流
	 * @param clz
	 *            对象类型
	 * @param startLine
	 *            开始行(包括标题所在行)
	 * @param tailLine
	 *            忽略的底部行数
	 * @return
	 */
	public <T> List<T> readExcelAsBean(InputStream inputStream, Class<T> clz, int startLine, int tailLine) {
		try {
			Workbook workbook = WorkbookFactory.create(inputStream);
			return readExcelAsBean(workbook, clz, startLine, tailLine);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			log.warn(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			log.warn(e.toString());
		}
		return null;
	}

	private <T> List<T> readExcelAsBean(Workbook workbook, Class<T> classOfT, int startLine, int tailLine) {
		Sheet sheet = workbook.getSheetAt(0);
		List<T> data = new ArrayList<>();
		try {
			Row row = sheet.getRow(startLine);
			Map<Integer, String> headerMap = getHeaderMap(row, classOfT);
			for (int i = startLine + 1; i <= sheet.getLastRowNum() - tailLine; i++) {
				row = sheet.getRow(i);
				T obj = classOfT.newInstance();
				for (Cell cell : row) {
					int columnIndex = cell.getColumnIndex();
					if (headerMap.containsKey(columnIndex)) {
						String javaObjectFieldName = headerMap.get(columnIndex);
						BeanUtils.copyProperty(obj, javaObjectFieldName, getCellValue(cell));
					}
				}
				data.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.warn(e.toString());
		}

		return data;
	}

	private List<ExcelHeaderField> getExcelHeaderFields(@SuppressWarnings("rawtypes") Class clz) {
		List<ExcelHeaderField> excelHeaderFields = new ArrayList<ExcelHeaderField>();
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ExcelField.class)) {
				ExcelField excelFieldAnnotation = field.getAnnotation(ExcelField.class);
				excelHeaderFields.add(new ExcelHeaderField(excelFieldAnnotation.title(), field.getName()));
			}
		}
		return excelHeaderFields;
	}

	private Map<Integer, String> getHeaderMap(Row titleRow, @SuppressWarnings("rawtypes") Class clz) {
		List<ExcelHeaderField> excelHeaderFields = getExcelHeaderFields(clz);
		Map<Integer, String> headerMap = new HashMap<Integer, String>();
		for (Cell cell : titleRow) {
			String title = cell.getStringCellValue().trim();
			for (ExcelHeaderField headerField : excelHeaderFields) {
				if (headerField.getExcelFieldName().equals(title)) {
					headerMap.put(cell.getColumnIndex(), headerField.getJavaObjectFieldName());
					break;
				}
			}
		}
		return headerMap;
	}

	private Object getCellValue(Cell cell) {
		Object value = null;
		switch (cell.getCellTypeEnum()) {
		case BLANK:
			break;
		case BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case FORMULA:
			value = cell.getCellFormula();
			break;
		case NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case STRING:
			value = cell.getStringCellValue().trim();
			break;
		default:
			value = cell.getStringCellValue().trim();
			break;
		}
		return value;
	}
}
