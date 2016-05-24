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
        </div>

        <div class="container">
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



            <div class="row">
                <div class="col-md-6">
                    <h3 class="page-header">Lista UNDO</h3>

                    <%
                        List<Integer> undo = (List<Integer>) request.getAttribute("undo");
                    %>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Id Transação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Integer i : undo) {%>
                            <tr>
                                <td><%=i%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>

                </div>
                <div class="col-md-6">
                    <h3 class="page-header">Lista REDO</h3>

                    <%
                        List<Integer> redo = (List<Integer>) request.getAttribute("redo");
                    %>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Log</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Integer j : redo) { %>
                            <tr>
                                <td><%=j%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                   <a href="Sistema.Index"><button>Voltar</button></a>
                </div>
            </div>

        </div>

    </body>
</html>

