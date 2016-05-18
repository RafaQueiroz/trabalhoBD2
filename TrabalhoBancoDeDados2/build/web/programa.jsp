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
            <div class="row">
                <h3 class="page-header">O que você deseja fazer?</h3>
            </div>

            <div>
                <form action="StartTransaction">
                    <input type="submit" value="Começar">
                </form>
                <form action="Update">
                    <label>UPDATE produtos SET NOME=
                        <input type="text" name="novoNome">
                    </label>
                    <label>WHERE id=
                        <select name="idLinha">
                            <%                                
                                String aux = (String) request.getAttribute("totalDadosDisco");
                                int totalDadosDisco = Integer.parseInt(aux);
                                
                                for (int i = 0; i < totalDadosDisco; i++) {
                            %>
                            <option value="<%=i%>"><%=i%></option>
                                <%}%>
                        </select>
                    </label>
                        <label>
                            na transacao = 
                        <select name="idTransacao">
                            <%
                                String aux2 = (String) request.getAttribute("totalTransacoes");
                                int totalTransacoes = Integer.parseInt(aux2);
                                for (int i = 0; i < totalTransacoes; i++) {
                            %>
                            <option value="<%=i%>"><%=i%></option>
                            <%}%>
                        </select>
                    </label>
                    <input type="submit" value="Executar">
                </form>

                <form action="FinalizarTransacao">
                    <label>
                        <select name="commitId">
                            <%
                                for (int i = 0; i < totalTransacoes; i++) {
                            %>
                            <option value="<%=i%>"><%=i%></option>
                            <%}%>
                        </select>
                    </label>
                    <input type="submit" value="Commit"> 
                </form>

                <form action="Checkpoint">
                    <input type="submit" value="Checkpoint"> 
                </form>
                        
                <form action="RecuperarFalha">
                    <input type="submit" value="Interface de recuperação de falhas"> 
                </form>

            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h3 class="page-header">Dados Buffer</h3>

                    <%
                        List<Produto> dadosBuffer = (List<Produto>) request.getAttribute("dadosBuffer");
                    %>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nome</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Produto p : dadosBuffer) {%>
                            <tr>
                                <td><%=p.getId()%></td>
                                <td><%=p.getNome()%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>

                </div>
                <div class="col-md-6">
                    <h3 class="page-header">Log Buffer</h3>

                    <%
                        List<TipoLog> logBuffer = (List<TipoLog>) request.getAttribute("logBuffer");
                    %>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Log</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (TipoLog l : logBuffer) {
                                    String saida = l.toString();
                            %>
                            <tr>
                                <td><%=saida%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <h3 class="page-header">Dados Disco</h3>

                    <%
                        List<Produto> dadosDisco = (List<Produto>) request.getAttribute("dadosDisco");
                    %>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nome</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Produto p : dadosDisco) {%>
                            <tr>
                                <td><%=p.getId()%></td>
                                <td><%=p.getNome()%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>

                </div>
                <div class="col-md-6">
                    <h3 class="page-header">Log Disco</h3>

                    <%
                        List<TipoLog> logDisco = (List<TipoLog>) request.getAttribute("logDisco");
                    %>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Log</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (TipoLog l : logDisco) {
                                    String saida = l.toString();
                            %>
                            <tr>
                                <td><%=saida%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>

    </body>
</html>

