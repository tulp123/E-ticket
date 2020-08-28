<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Ticket information</title>
</head>

<style>
    body {
        font-family: 'Roboto', sans-serif;
        font-size: 48px;
        margin: 0;
        padding: 0;
    };

    table {
        align="center";
        border="0";
        cellpadding="0";
        cellspacing="0";
        width="600";
        border-collapse: collapse;
    }
</style>
<body>
    <ul>
        <#list listTicket as ticket>
            <table width="100%">
                <tr>
                    <td align="center" valign="top" bgcolor="#6C7B8B"
                        style="background-color: #6C7B8B;"><br> <br> <br>
                        <table width="900px">
                            <tr>
                                <td align="center" valign="top" bgcolor="#d3be6c"
                                    style="background-color: #FFFAF0; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px 15px;">
                                    <br>
                                    <div>
                                        <img src="${qrcode}" style=" width: 150px; height: 150px; background-size: contain">
                                    </div>

                                    <div>Serial number: ${ticket.ticketSerial}</div><br>

                                    <div style="font-size: 48px; color: #000080;">
                                        <b>${ticket.event.game.aTeamName} - ${ticket.event.game.bTeamName}</b>
                                    </div>

                                    <div style="font-size: 24px; color: #000000;">
                                        <br>${ticket.event.locationName}
                                        <br>${ticket.event.game.matchTime?datetime?string("HH:mm:ss EEEE, dd/MM/yyyy")}
                                        <br>${ticket.seat.area.name}
                                    </div> <br>
                                </td>
                            </tr>
                        </table> <br> <br> <br>
                    </td>
                </tr>
            </table>
        </#list>
    </ul>
</body>
</html>
