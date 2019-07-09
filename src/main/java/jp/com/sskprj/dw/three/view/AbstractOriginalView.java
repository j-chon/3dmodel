package jp.com.sskprj.dw.three.view;

import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;

public abstract class AbstractOriginalView extends View {

    public AbstractOriginalView(String templateName) {
        super(templateName, StandardCharsets.UTF_8);
    }

    /**
     * 表示確認用のダミーデータ設定処理を記述する。
     * (テスト用なので本機能実装では使わないこと。)
     */
    protected abstract void initDummyData();

    /**
     * 自身のダミーデータ入りViewを返す。
     * ※表示確認用画面のみで使用
     * (テスト用なので本機能実装では使わないこと。)
     *
     * @return
     */
    public AbstractOriginalView getDummyView() {
        this.initDummyData();
        return this;
    }

}
