<%@page import="Log.Update"%>
<%@page import="Log.TipoLog"%>
<%@page import="java.util.List"%>
<%@page import="Models.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trabalho de Banco de Dados</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="menu.jsp" %>
        <div class="container">
            
            <h3 class="page-header">Projeto de aplicativo de Banco de Dados</h3>
            <h4 class="page-header">Grupo : Rhuan Barros Rafael Queiroz</h4>
            
            <a href="/TrabalhoBancoDeDados2/CriaArquivoDadosDiscoParaApresentacao">Inicializa aplicação com dados iniciais no banco de dados.</a>
            <br>
            <a href="/TrabalhoBancoDeDados2/Programa">Interface de manipulação de dados</a>
            <br>
            <a href="/TrabalhoBancoDeDados2/RecuperarFalha">Realizar recuperação de falhas Undo e Redo</a>
            
        </div>

    </body>
</html>
