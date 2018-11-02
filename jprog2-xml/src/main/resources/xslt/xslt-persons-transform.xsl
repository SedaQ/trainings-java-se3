<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : xslt-persons-transform.xsl
    Author     : Seda
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <h2>
                <b>
                    <big>Persons</big>
                    <sup>overview</sup>
                </b>
            </h2>
            <body>
                <!-- <link rel="stylesheet" type="text/css" href="style/cascadeStyleSheet.css"/> -->
                <style>
                    table {
                    width:100%;
                    }
                    table, th, td {
                    border: 1px solid black;
                    border-collapse: collapse;
                    }
                    th, td {
                    font-size: 1.2em;
                    border: 1px solid #98BF21;
                    padding: 3px 7px 2px;
                    }

                    table tr:nth-child(even) {
                    background-color: #eee;
                    }
                    table tr:nth-child(odd) {
                    background-color:#fff;
                    }
                    table th {
                    font-size: 1.4em;
                    text-align: left;
                    padding-top: 5px;
                    padding-bottom: 4px;
                    background-color: #A7C942;
                    color: #EEE;
                    }
                </style>
                <table>
                    <tr>
                        <th style="text-align:left">person id</th>
                        <th style="text-align:left">first name</th>
                        <th style="text-align:left">last name</th>
                    </tr>
                    <xsl:for-each select="content/person">
                        <tr>
                            <td>
                                <xsl:value-of select="person_id"/>
                            </td>
                            <td>
                                <xsl:value-of select="first_name"/>
                            </td>
                            <td>
                                <xsl:value-of select="last_name"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
