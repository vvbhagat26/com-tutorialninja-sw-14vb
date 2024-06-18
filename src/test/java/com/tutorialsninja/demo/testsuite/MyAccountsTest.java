package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.customlisteners.CustomListeners;
import com.tutorialsninja.demo.pages.*;
import com.tutorialsninja.demo.testbase.TestBase;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(CustomListeners.class)
public class MyAccountsTest extends TestBase {
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    AccountPage accountPage;
    LogoutPage logoutPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        accountPage = new AccountPage();
        logoutPage = new LogoutPage();
    }

    /**
     * 1.1 create method with name "selectMyAccountOptions" it has one parameter name "option" of type string
     * 1.2 This method should click on the options whatever name is passed as parameter.
     * (Hint: Handle List of Element and Select options)
     */
    public void selectMyAccountOptions(String option) {
        List<WebElement> options = homePage.getMyAccountMenuOptions();
        try {
            for (WebElement optionName : options) {
                if (optionName.getText().equalsIgnoreCase(option)) {
                    optionName.click();
                }
            }

        } catch (StaleElementReferenceException e) {
            options = homePage.getMyAccountMenuOptions();
        }

    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        //1.1 Click on My Account Link.
        homePage.clickOnMyAccountMenu();
        //1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");
        //1.3 Verify the text “Register Account”.
        Assert.assertEquals(registerPage.getRegisterAccountText(), "Register Account", "Register Account text is not matched");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        //2.1 Click on My Account Link.
        homePage.clickOnMyAccountMenu();
        //2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");
        //2.3 Verify the text “Returning Customer”.
        Assert.assertEquals(loginPage.getReturningCustomerText(), "Returning Customer", "Returning Customer text is not matched");
    }

    @Test(groups = "regression")
    public void verifyThatUserRegisterAccountSuccessfully() {
        //3.1 Click on My Account Link.
        homePage.clickOnMyAccountMenu();
        //3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");
        //3.3 Enter First Name
        registerPage.enterTextToFirstName("Test First Name");
        //3.4 Enter Last Name
        registerPage.enterTextToLastName("Test Last Name");
        //3.5 Enter Email
        registerPage.enterTextToEmail("testfnln" + currentTimeStamp() + "@gmail.com");
        //3.6 Enter Telephone
        registerPage.enterTextToTelephone("04532157543");
        //3.7 Enter Password
        registerPage.enterTextToPassword("test@123");
        //3.8 Enter Password Confirm
        registerPage.enterTextToPasswordConfirm("test@123");
        //3.9 Select Subscribe Yes radio button
        registerPage.clickOnYesRadioOption();
        //3.10 Click on Privacy Policy check box
        registerPage.clickOnTermsAndAgreement();
        //3.11 Click on Continue button
        registerPage.clickOnContinueButton();
        // 3.12 Verify the message “Your Account Has Been Created!”
        Assert.assertEquals(accountPage.getAccountCreatedSuccessMessageText(), "Your Account Has Been Created!", "Your Account Has Been Created! text is not matched");
        // 3.13 Click on Continue button
        accountPage.clickOnContinueButton();
        // 3.14 Click on My Account Link.
        accountPage.clickOnMyAccountLink();
        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        //3.16 Verify the text “Account Logout”
        Assert.assertEquals(logoutPage.getAccountLogoutText(), "Account Logout", "Account Logout text is not matched");
        //3.17 Click on Continue button
        logoutPage.clickOnContinueButton();
    }

    @Test(groups = {"regression"})
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        //4.1 Clickr on My Account Link.
        homePage.clickOnMyAccountMenu();
        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");
        //4.3 Enter Email address
        loginPage.enterTextToEmailAddress("testfntestln12@gmail.com");
        //4.4 Enter Last Name --- this is N/A
        //4.5 Enter Password
        loginPage.enterTextToPassword("test@123");
        //4.6 Click on Login button
        loginPage.clickOnLoginButton();
        //4.7 Verify text “My Account”
        Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "My Account text is not matched");
        //4.8 Click on My Account Link.
        accountPage.clickOnMyAccountLink();
        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        //4.10 Verify the text “Account Logout”
        Assert.assertEquals(logoutPage.getAccountLogoutText(), "Account Logout", "Account Logout text is not matched");
        //4.11 Click on Continue button
        logoutPage.clickOnContinueButton();
    }
}
