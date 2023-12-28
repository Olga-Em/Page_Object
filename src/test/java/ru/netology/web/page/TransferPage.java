package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement moneyInput = $("[data-test-id=amount] input");
    private final SelenideElement cardInput = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amountToTransfer, DataHelper.Card card) {
        makeTransfer(amountToTransfer, card);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.Card card) {
        moneyInput.setValue(amountToTransfer);
        cardInput.setValue(card.getCardNumber());
        transferButton.click();
    }

    public void setErrorMessage(String textError) {
        errorMessage.shouldHave(exactText(textError), Duration.ofSeconds(10)).shouldBe(visible);
    }


}
