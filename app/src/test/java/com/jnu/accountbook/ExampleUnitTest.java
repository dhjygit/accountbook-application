package com.jnu.accountbook;

import org.junit.Test;
import com.jnu.accountbook.bean.AccountBean;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int id = 1;
        int imageId = 1;
        String typename = "娱乐";
        String accountType = "支付宝";
        String remark = "hello world";
        float money = 100f;
        String date = "2021-12-24";
        AccountBean accountBean = new AccountBean(id, imageId, typename, accountType, remark, money, date);
        System.out.println(accountBean.getAccountType());
        System.out.println(accountBean.getDate());
    }
}