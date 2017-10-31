package lgh.lib.excel.extractor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExcelHeaderField {
	private String excelFieldName;
	private String javaObjectFieldName;
}