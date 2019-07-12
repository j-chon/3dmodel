<!DOCTYPE html>
<#-- @ftlvariable name="" type="jp.com.sskprj.dw.three.view.ReserveCompletedView" -->
<html lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="copyright" content="Copyright ©">
    <meta name="robots" content="index, follow">
    <title>${viewHeaderData.title}</title>
    <link rel="canonical" href="">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/assets/css/BPRV.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/global.css" rel="stylesheet" type="text/css">
    <link href="/assets/css/common.css" rel="stylesheet" type="text/css">
</head>
<body id="pageTop">
<!-- ↓header -->
<div id="reserveHeaderWrapper">
    <div id="header">
        <div id="smallLogoNavi">
            <div id="logo">
                <a href="/" class="smallLogo cS offL">検索・予約サイト</a>
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

        <div class="rsvSalonHeader">
            <div class="cFix">
                <span class="rsvType">仮予約</span>
                <p class="rsvTypeLead">
                    ※まだ確定ではありません。
                </p>
            </div>
            <div class="rsvSalonNameWrap cFix">
                <h2 class="rsvSalonName">${storeName}</h2>
                <p class="fl">${storeName}</p>
            </div>
        </div>
        <ol class="rsvStepList cFix">
            <li class="rslStep2 passive"><span>STEP2</span>日時を指定する</li>
            <li class="rslStep3 passive"><span>STEP3</span>お客様情報入力</li>
            <li class="rslStep4 active "><span>STEP4</span>予約内容の確認</li>
            <li class="rslStepEnd ">予約完了</li>
        </ol>
        <form id="bt_reserveActionForm" method="post" action="/reserve/complete/">
            <input type="hidden" name="storeId" value="${storeId}">
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
                                   value="${reserveForm.isMailMagazineReceive}"
                                   checked="checked"
                                   class="cbF"><label for="forRsvcmReceive">受け取る</label>
                        </div>
                        <div class="fgOrange dibBL vaM fs10 pR5 pL1e txt1e">
                            ※店舗からのメッセージがマイページに届きます。メッセージ自体を受けとりたくない場合はマイページから解除出来ます。
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="yS reserveTitleOuter mT20">
                <h3 class="yS reserveTitleInner pL10 fw_bold">店舗からお客様への確認事項</h3>
            </div>
            <table cellspacing="0" class="wFull bdCell mT10">
                <tbody>
                <tr>
                    <th class="w170 bgLGray3">
                        <div class="dibBL vaM fw_normal iconMust mR10">必須</div>
                        <div class="dibBL vaM w105">ご来店に際しての<br>注意事項</div>
                    </th>
                </tr>
                </tbody>
            </table>
            <div class="mT20">
                <a href="javascript: void(0);" class="moreDetailRequestInput" id="jsiMoreDetailRequestInput">
                    <span class="moreDetailRequestInputText iS arrowR"
                          id="jsiMoreDetailRequestInputText">詳細要望を入力する（任意）</span>
                    <span class="fgGray"
                          id="jsiMoreDetailRequestInputSubText">（スタイリストに関する要望・スタイリストへのご相談・なりたいイメージなど）</span>
                </a>
            </div>

            <div class="moreDetailRequestInputArea" id="jsiMoreDetailRequestInputArea">
                <div class="moreDetailRequestInputAreaInner" id="jsiMoreDetailRequestInputAreaInner">
                    <div class="yS reserveTitleOuter mT15">
                        <h3 class="yS reserveTitleInner pL10 fw_bold">より詳しいご要望がある場合はこちらからご入力ください</h3>
                    </div>

                    <table cellspacing="0" class="wFull bdCell  mT10">
                        <tbody>
                        <tr>
                            <th colspan="2" class="w160 bgLGray3 fw_bold">スタイリストに関するご希望</th>
                        </tr>
                        <tr>
                            <th class="w170 bgLGray fw_bold">カウンセリングで知りたい<br>こと</th>
                            <td class="lh18">
                                <ul class="cFix">
                                    <li class="fl w205 nowrap">
                                        <input type="checkbox" name="rsvDemandCounselingCd" value="1"
                                               id="forMyStyleToSuit" class="cbF"><label for="forMyStyleToSuit">自分に似合うスタイルについて</label>
                                    </li>
                                    <li class="nowrap">
                                        <input type="checkbox" name="rsvDemandCounselingCd" value="2"
                                               id="forEverydayStyling" class="cbF"><label for="forEverydayStyling">普段のスタイリングについて</label>
                                    </li>
                                    <li class="fl w205 nowrap">
                                        <input type="checkbox" name="rsvDemandCounselingCd" value="4"
                                               id="arrangeForStyle" class="cbF"><label for="arrangeForStyle">スタイルのアレンジについて</label>
                                    </li>
                                    <li class="nowrap">
                                        <input type="checkbox" name="rsvDemandCounselingCd" value="8"
                                               id="otherFeaturedMenu" class="cbF"><label for="otherFeaturedMenu">その他おすすめのメニュー（トリートメントなど）について</label>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <th class="w170 bgLGray fw_bold">接客へのご要望</th>
                            <td class="vaM lh18">
                                <ul class="cFix">
                                    <li class="fl mR10 nowrap">
                                        <div class="dibBL vaM">${reserveForm.customerRequest}</div>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <table cellspacing="0" class="wFull bdCell  mT10">
                        <tbody>
                        <tr>
                            <th class="bgLGray3 fw_bold">ご要望・ご相談</th>
                        </tr>
                        <tr>
                            <td>
                                <div>
                                    <textarea name="cast_request_detail" cols="141" rows="3"
                                              class="taF w730 h55 imeOn" style="color: rgb(204, 204, 204);"></textarea>
                                </div>
                                <p>（全角200文字以内）</p>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <table cellspacing="0" class="wFull bdCell  mT10">
                        <tbody>
                        <tr>
                            <th colspan="2" class="w160 bgLGray3 fw_bold">なりたいイメージ</th>
                        </tr>
                        <tr>
                            <th class="w170 bgLGray fw_bold">なりたいイメージ</th>
                            <td class="vaM lh23 cFix">
                                <div class="dibBL vaM">${reserveForm.textHairImage}</div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>


            <table cellspacing="0" class="wFull bdCell  mT10">
                <tbody>
                <tr>
                </tr>
                <tr>
                    <th class="w170 bgLGray fw_bold">合計金額</th>
                    <td class="lh18" colspan="2">
                        <div class="dibBL vaM w150">¥<span id="rsvTotalPrice">${reserveForm.totalCharge}</span></div>
                        <div class="fgOrange dibBL vaM fs10 pL1e txt1e w400">※端数は切り下げです。</div>
                    </td>
                </tr>

                <tr>
                </tr>

                <tr>
                </tr>
                <tr>
                    <th class="w170 bgLGray fw_bold">お支払い予定金額<br><span class="fw_normal">（合計金額 - ご利用ポイント分）</span></th>
                    <td class="lh18" colspan="2">
                        <div class="dibBL vaM w150">
                            <span class="fw_bold" id="paymentAmount">¥12,960</span></div>
                        <div class="fgOrange dibBL vaM fs10">
                            <p class="pL1e txt1e w400">※予約時の選択メニューや、来店時のメニュー変更により、実際の支払い金額と異なる場合があります。</p>
                            <p class="pL1e txt1e w400">
                                ※2019年10月1日以降のご来店予定の場合、消費税率引上げに伴う料金改定により消費税の差額分、記載料金より高くなる場合があります。</p>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>


            <p class="pV10">※メルマガの購読を希望されない方はページ下部の「メルマガ配信」をご確認下さい</p>
            <div class="submitArea2 pr">
                <div>
                    <input type="submit" name="confirm" value="予約内容を確認する" class="bS confirmL db offL mHA pointerCursor"
                           title="予約内容を確認する"></div>
            </div>

            <div class="mT30">
                <dl class="mmSettingBox" id="jsiMmSettingBox" style="border-radius: 6px;">
                    <dt class="mmSettingHeadline">メールマガジン受信設定</dt>
                    <dd class="mmSettingBody">
                        <div class="mmSettingBodyNotes pClear">
                            <p class="fs11">入力された会員ID（メールアドレス）に会員様限定のお知らせメールをお送りいたします。<br>配信を希望されない場合は、チェックをお外しください。</p>
                        </div>
                    </dd>
                    <dd class="mmSettingBody cFix">
                        <p>最新情報をお届けします。</p>
                        <p class="fl mR30">
                            <input type="checkbox" name="mailmagazineType" value="1"
                                   id="forMailmagazineType" class="cbF"><label for="forMailmagazineType">ホットペッパービューティーメルマガ</label>
                        </p>
                    </dd>
                    <dd class="mmSettingBody cFix">
                        <p class="fl mR30">
                            <input type="checkbox" name="capMlMagznDeliKbn" value="H"
                                   id="forCapMlMagznDeliKbn" class="cbF"><label
                                    for="forCapMlMagznDeliKbn">おしらせ</label></p>
                        <p class="fl"><a href="/" target="_blank">詳細はこちら</a>
                        </p>
                    </dd>
                </dl>
            </div>
            <input type="hidden" name="rsvStyleStyleId" value="">
            <input type="hidden" name="rsvStyleStyleName" value="">
            <input type="hidden" name="rsvStyleStyleFilename" value="">
            <input type="hidden" name="flgDetail" value="" id="flgDetail">
            <input type="submit" value="確認画面へ"/>
        </form>
        <div class="mT10">
            <a href="/reserve/000376765"
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
