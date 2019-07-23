<!DOCTYPE html>
<#-- @ftlvariable name="" type="jp.com.sskprj.dw.three.view.ReserveInputView" -->
<html lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="copyright" content="Copyright ©">
    <meta name="robots" content="index, follow">
    <title>お客様情報入力</title>
    <link rel="canonical" href="">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/assets/css/BPRV.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/global.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/common.css" rel="stylesheet" type="text/css">
</head>
<body id="pageTop" style="">
<!-- ↓header -->
<div id="reserveHeaderWrapper">
    <div id="header">
        <div id="smallLogoNavy">
            <div id="logo">
                <a href="/" class="smallLogo cS offL">検索・予約サイト</a>
            </div>
        </div>
    </div>
</div>
<!-- ↑header -->
<!-- ↓wrapper -->
<div id="reserveWrapper">
    <div class="w_break_all mT5"></div>
    <!-- ↓contents -->
    <div id="reserveContents">
        <div class="reserveHeader">
            <div class="cFix">
                <span class="rsvType">即時予約</span>
                <p class="rsvTypeLead">
                    ※即時予約は、予約完了時点で予約が確定します。そのままご来店ください。
                </p>
            </div>
            <div class="rsvStoreNameWrap cFix">
                <h2 class="rsvStoreName">${storeName}</h2>
                <p class="fl">${storeName}</p>
            </div>
        </div>
        <ol class="rsvStepList cFix">
            <li class="rslStep2 passive"><span>STEP2</span>日時を指定する</li>
            <li class="rslStep3 active"><span>STEP3</span>お客様情報入力</li>
            <li class="rslStep4 "><span>STEP4</span>予約内容の確認</li>
            <li class="rslStepEnd ">予約完了</li>
        </ol>
        <form id="bt_reserveActionForm" method="post" action="/reserve/confirm/">
            <input type="hidden" name="csrf_token" value="${csrfToken}"/>
            <input type="hidden" name="storeId" value="${storeId}">
            <div class="yS reserveTitleOuter mT20">
                <h3 class="yS reserveTitleInner pL10 fw_bold">お客様情報を入力してください</h3>
            </div>
            <table cellspacing="0" class="wFull bdCell  mT10">
                <tbody>
                <tr>
                    <th class="w170 bgLGray fw_bold">
                        <div class="dibBL vaM w105">ご予約者氏名</div>
                    </th>
                    <td class="vaThT">
                        <input type="text" maxlength="30" value="${reserveForm.customerName}" name="cst_name"
                               class="tfFR w110 imeOff">
                    </td>
                </tr>
                <tr>
                    <th class="w170 bgLGray fw_bold">
                        <div class="dibBL vaM w105">ご予約者住所</div>
                    </th>
                    <td class="vaThT">
                        <input type="text" maxlength="7" value="" name="cst_post_num"
                               class="tfFR w110 imeOff">
                        <input type="text" maxlength="200" value="" name="cst_address"
                               class="tfFR w110 imeOff">
                        <input type="text" maxlength="200" value="" name="cst_address_01"
                               class="tfFR w110 imeOff">
                    </td>
                </tr>
                <tr>
                    <th class="w170 bgLGray3">
                        <div class="dibBL vaM fw_normal iconMust mR10">必須</div>
                        <div class="dibBL vaM w105">連絡可能な<br>電話番号</div>
                        <p class="dibBL vaM taR fw_normal">半角<br>数字</p>
                    </th>
                    <td class="vaThT">
                        <div class="mT5">
                            <input type="text" name="cst_phone_num" value="${reserveForm.customerPhoneNumber}"
                                   maxlength="17" class="tfFR w110 imeOff">
                            <span class="mL5">（例）030000000</span>
                            <p class="fs10 mT2">※入力された電話番号は会員情報に反映されます。</p>

                        </div>
                    </td>
                </tr>
                <tr>
                    <th class="w170 bgLGray3">
                        <div class="dibBL vaM fw_normal iconMust mR10">必須</div>
                    </th>
                    <td class="vaM">
                        <ul class="cFix">
                        </ul>
                    </td>
                </tr>
                <tr>
                    <th class="w170 bgLGray fw_bold taC">
                        <div class="dibBL vaM">店舗からのメッセージ</div>
                    </th>
                    <td class="lh18">
                        <div class="dibBL vaM w150">
                            <input type="checkbox" name="is_mail_magazine_rcv"
                                   value="1"
                                   checked="checked" class="cbF">
                            <label for="forRsvcmReceive">受け取る</label>
                        </div>
                        <div class="fgOrange dibBL vaM fs10 pR5 pL1e txt1e">
                            ※店舗からのメッセージがマイページに届きます。メッセージ自体を受けとりたくない場合はマイページから解除出来ます。
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>

            <table cellspacing="0" class="wFull bdCell  mT10">
                <tbody>
                <tr>
                    <th class="w170 bgLGray fw_bold">合計金額</th>
                    <td class="lh18" colspan="2">
                        <div class="dibBL vaM w150">¥<input type="number" name="total_charge" value="0"></div>
                        <div class="fgOrange dibBL vaM fs10 pL1e txt1e">※端数は切り下げです。</div>
                    </td>
                </tr>
                </tbody>
            </table>
            <input type="submit" value="確認画面へ"/>
        </form>

    </div>
    <!-- ↑contents -->
    <!-- ↓footer -->
    <div id="reserveFooter">
        <div id="copyrightnew">
            <a href="" target="_blank"><img
                        src="/assets/image/footercopyright.gif" alt="(C)"></a>
        </div>
        <ul id="footerLinks">
            <li><a href="javascript:void(0);" id="mterms">ID・会員規約</a></li>
            <li><a href="javascript:void(0);" id="terms">利用規約</a></li>
            <li><a href="javascript:void(0);" id="privacy">プライバシーポリシー</a></li>
            <li><a href="javascript:void(0);" id="guide">ご利用ガイド</a></li>
        </ul>
    </div>
</div>
</html>
