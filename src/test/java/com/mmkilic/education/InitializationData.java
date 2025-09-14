package com.mmkilic.education;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mmkilic.education.entity.Word;
import com.mmkilic.education.service.WordService;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
public class InitializationData {
    @Autowired
    private WordService wordService;
    
    
	@Test
	void contextLoads() {
		try {
			saveWords();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
    void saveWords() throws Exception{
    	try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("init/words.xlsx")) {
            if (inputStream == null) {
                throw new FileNotFoundException("Excel file not found in resources");
            }
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                Word word = Word.builder()
                        .english(getCellValue(row.getCell(1)))
                        .turkish(getCellValue(row.getCell(2)))
                        .synonym(getCellValue(row.getCell(3)))
                        .sentence(getCellValue(row.getCell(4)))
                        .build();

                wordService.save(word);
            }

            workbook.close();
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to import users", e);
        }
    	
        System.out.println("words saved!");
    }
    
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }
    
    
    @SuppressWarnings("unused")
	private double getCellValueDouble(Cell cell) {
        if (cell == null) return 0;
        return switch (cell.getCellType()) {
            case NUMERIC -> Double.valueOf(cell.getNumericCellValue());
            default -> 0;
        };
    }
}
