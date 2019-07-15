<!DOCTYPE html>
<#-- @ftlvariable name="" type="jp.com.sskprj.dw.three.view.ReserveCalenderView" -->
<html lang="ja">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <title>予約</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/calendar.css">
    <style>
        body {
            background-color: #cce0ff;
            color: #000;
        }

        a {
            color: #080;
        }
    </style>
</head>
<body>
<h1>こんにちは, ${viewHeaderData.title}!</h1>


<div id="app">
    <table class="calendar">
        <caption><a class="prevnext" href="?y=2018&amp;m=12">＜＜</a><span class="title">${calendarDto.targetMonth}</span><a
                    class="prevnext"
                    href="?y=2019&amp;m=2">＞＞</a>
        </caption>
        <tbody>
        <tr>
            <th class="sunday">${calendarDto.titleList[0]}</th>
            <th class="weekday">${calendarDto.titleList[1]}</th>
            <th class="weekday">${calendarDto.titleList[2]}</th>
            <th class="weekday">${calendarDto.titleList[3]}</th>
            <th class="weekday">${calendarDto.titleList[4]}</th>
            <th class="weekday">${calendarDto.titleList[5]}</th>
            <th class="saturday">${calendarDto.titleList[6]}</th>
        </tr>
        <#list calendarDto.dayList as item>
            <tr>
                <td class="sunday"><span>${item[0]}</span></td>
                <td class=""><span>${item[1]}</span></td>
                <td class="holiday"><span>${item[2]}</span>
                    <p class="holidays_name">元日</p></td>
                <td class=""><span>${item[3]}</span></td>
                <td class=""><span>${item[4]}</span></td>
                <td class=""><span>${item[5]}</span></td>
                <td class="saturday"><span>${item[6]}</span></td>
            </tr>
        </#list>
    </table>
</div>

</body>
</html>
