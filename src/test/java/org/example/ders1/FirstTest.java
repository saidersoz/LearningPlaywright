package org.example.ders1;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {

    static Playwright playwright;
    static Browser browser;

    @BeforeAll
    static void setup() {
        // Playwright başlat
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        // headless(false) → tarayıcıyı görsel olarak açar, istersen true yapıp gizleyebilirsin
    }

    @AfterAll
    static void teardown() {
        // İş bitince kapat
        browser.close();
        playwright.close();
    }

    @Test
    void openGoogleAndSearch() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.google.com");

        // Sayfa başlığı kontrol et
        String title = page.title();
        System.out.println("Title: " + title);

        assertTrue(title.contains("Google"));

        context.close();
    }

}
