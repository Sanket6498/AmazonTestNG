package com.amazon.login_Functionality;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonLogin {

	WebDriver driver;
	AmazonPOM amazonPOM;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.get(
				"https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fb%2Fref%3Dsurl_fashion%2F%3F_encoding%3DUTF8%26_encoding%3DUTF8%26content-id%3Damzn1.sym.083813c2-fda2-448d-b747-552a1b3b7e02%26node%3D6648217031%26pd_rd_r%3D33d7417e-a66c-45cb-999d-2b295990dfe2%26pd_rd_w%3DQMhoA%26pd_rd_wg%3D0wzCB%26pf_rd_p%3D083813c2-fda2-448d-b747-552a1b3b7e02%26pf_rd_r%3DSAZC656XFJ7HRXCGX4TZ%26ref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		amazonPOM = new AmazonPOM(driver);
	}

	@Test
	public void checkValidation() throws Exception {
		String path = "S:\\Automation\\Excel\\Amazon.xlsx";
		FileInputStream fileInputStream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum();
		for (int i = 1; i <= lastRow; i++) {
			amazonPOM.setEmail(sheet.getRow(i).getCell(0).toString());
			amazonPOM.continueButton();
			if (driver.getTitle().contains("Amazon Sign In")) {
				Row row = sheet.getRow(i);
				Cell cell = row.createCell(1);
				cell.setCellValue("Valid");
				FileOutputStream fileOutputStream = new FileOutputStream(path);
				workbook.write(fileOutputStream);
				fileOutputStream.close();
				driver.navigate().back();
				amazonPOM.emailId.clear();
			} else {
				Row row = sheet.getRow(i);
				Cell cell = row.createCell(1);
				cell.setCellValue("Invalid");
				FileOutputStream fileOutputStream = new FileOutputStream(path);
				workbook.write(fileOutputStream);
				fileOutputStream.close();
				amazonPOM.emailId.clear();
			}
		}
		workbook.close();
		fileInputStream.close();
	}
}
