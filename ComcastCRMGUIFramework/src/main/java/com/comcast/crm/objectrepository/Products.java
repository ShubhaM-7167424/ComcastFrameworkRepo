package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products{
	@FindBy (xpath = "//img[@alt='Create Product...']")
	private WebElement createProductImageBtn;
	
	@FindBy (xpath = "//input[@value=' Search Now ']")
	private WebElement ele3;
}
