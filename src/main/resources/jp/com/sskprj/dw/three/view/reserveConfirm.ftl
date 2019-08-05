<!DOCTYPE html>
<#-- @ftlvariable name="" type="jp.com.sskprj.dw.three.view.ReserveConfirmView" -->
<html lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="copyright" content="Copyright ©XXXXX">
    <meta name="robots" content="index, follow">
    <title>${viewHeaderData.title}</title>
    <link rel="canonical" href="">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/assets/css/reserve.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/global.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/common.css" rel="stylesheet" type="text/css">
</head>
<body id="pageTop">
<!-- ↓header -->
<div id="reserveHeaderWrapper">
    <div id="header">
        <div id="smallLogoNavy">
            <div id="logo">
                <a href="/" class="cS offL">検索・予約サイト</a>
            </div>
        </div>
    </div>
</div>
<!-- ↑header -->
<!-- ↓wrapper -->
<div id="reserveWrapper">
    <div class="w_break_all mT5">
    </div><!-- ↓contents -->
    <div id="reserveContents">

        <div class="commonHeader">
            <div class="cFix">
                <span class="rsvType">仮予約</span>
                <p class="rsvTypeLead">
                    ※まだ確定ではありません。
                </p>
            </div>
        </div>
        <ol class="rsvStepList cFix">
            <li class="rslStep2 passive"><span>STEP2</span>日時を指定する</li>
            <li class="rslStep3 passive"><span>STEP3</span>お客様情報入力</li>
            <li class="rslStep4 active "><span>STEP4</span>予約内容の確認</li>
            <li class="rslStepEnd ">予約完了</li>
        </ol>
        <form id="bt_reserveActionForm" method="post" action="/reserve/complete/">
            <input type="hidden" name="csrf_token_reserve" value="${csrfToken}"/>
            <div class="yS reserveTitleOuter mT20">
                <h3 class="yS reserveTitleInner pL10 fw_bold">お客様情報の確認</h3>
            </div>
            <table cellspacing="0" class="wFull bdCell  mT10">
                <tbody>
                <tr>
                    <th class="w170 bgLGray fw_bold">
                        <div class="dibBL vaM w105">ご予約者氏名</div>
                    </th>
                    <td class="vaThT">
                        <div class="dibBL vaM">${reserveForm.customerName}</div>
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
                            <div class="dibBL vaM">${reserveForm.customerPhoneNumber}</div>
                            <p class="fs10 mT2">※入力された電話番号は会員情報に反映されます。</p>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th class="w170 bgLGray3">
                        <div class="dibBL vaM fw_normal iconMust mR10">必須</div>
                    </th>
                </tr>
                <tr>
                    <th class="w170 bgLGray fw_bold taC">
                        <div class="dibBL vaM">店舗からのメッセージ</div>
                    </th>
                    <td class="lh18">
                        <div class="dibBL vaM w150">
                            <span>${mailMagazineReceiveCheckStatus}</span>
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
                        <div class="dibBL vaM w150">¥<span id="rsvTotalPrice">${reserveForm.totalCharge}</span></div>
                        <div class="fgOrange dibBL vaM fs10 pL1e txt1e w400">※端数は切り下げです。</div>
                    </td>
                </tr>
                </tbody>
            </table>

            <input type="submit" value="登録する"/>
        </form>
        <div class="mT10">
            <a href="/reserve/input/"
               title="戻る" class="btnCssWhite dib w100 pV8">戻る</a>
        </div>

    </div>
    <!-- ↑contents -->
    <!-- ↓footer -->
    <div id="reserveFooter">
        <div id="copyrightnew">
            <a href="" target="_blank"><img
                        src="./お客様情報入力_files/footercopyright.gif" alt="(C)"></a>
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
