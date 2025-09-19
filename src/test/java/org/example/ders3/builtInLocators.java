package org.example.ders3;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.awt.*;

public class builtInLocators {

    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int weidt = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().
                launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();

        page.setViewportSize(weidt,height);
        page.navigate("https://getir.com/");
        System.out.println("Ttile : " + page.title());

        //getByText()
        Locator loginText = page.getByText("Giriş yap veya kayıt ol");
        System.out.println("1. Text : " + loginText.innerText());

        Locator shadowDom = page.locator("div", new Page.LocatorOptions().setHasText("Giriş yap veya kayıt ol")).last();
        System.out.println("shadowDom : "+ shadowDom.innerText());

        //getByRole()
        Locator phoneNumber = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("phoneNumber : "+ phoneNumber.innerText());
        phoneNumber.click();
        phoneNumber.fill("545774");

        Thread.sleep(2000);

        //getByPlaceholder
        Locator phoneNumber2 = page.getByPlaceholder("Telefon Numarası");
        System.out.println("phoneNumber2 : "+ phoneNumber2.innerText());

        //getByLabel
        Locator phoneContinueButton = page.getByLabel("Login button");
        System.out.println(phoneContinueButton.innerText());


        //getByRole
        Locator loginButton = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Giriş yap"));
        loginButton.click();

        //testId
        Locator loginPhoneNumber = page.getByTestId("modal").getByPlaceholder("Telefon Numarası");
        System.out.println("5. Login Phone Number: " + loginPhoneNumber.innerText());
        loginPhoneNumber.click();
        loginPhoneNumber.fill("545774");

        Locator cancelButton = page.getByTestId("modal").getByTestId("button").first();
        cancelButton.click();


        //getByAllText
        Locator avarage = page.getByAltText("Su & İçecek");
        avarage.click();

        Thread.sleep(2000);


        //xpath Locator
        Locator ContinueButton = page.locator("(//button[@data-testid='button'])[6]");
        System.out.println("ContinueButton : " + ContinueButton.innerText());


        page.close();
        browser.close();
        playwright.close();
    }

}
