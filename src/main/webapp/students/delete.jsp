<%--
  Created by IntelliJ IDEA.
  User: jlkesh
  Date: 06/02/23
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<div class="row">
    <div class="col-md-10 offset-1">
<%--        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>--%>

<%--        <form method="post" action="/students/delete/${student.getId()}">--%>
<%--           <script>--%>
<%--               swal({--%>
<%--                   title: "Are you sure?",--%>
<%--                   text: "Really deleted this student",--%>
<%--                   icon: "warning",--%>
<%--                   buttons: true,--%>
<%--                   dangerMode: true,--%>
<%--               })--%>
<%--                   .then((willDelete) => {--%>
<%--                       if (willDelete) {--%>
<%--                           swal("Poof! This student has been deleted!", {--%>
<%--                               icon: "success",--%>
<%--                           });--%>
<%--                       } else {--%>
<%--                           swal("Cancel deleted!");--%>
<%--                       }--%>
<%--                   });--%>
<%--            </script>--%>
<%--        </form>--%>
<%--    <form id="id"--%>
<%--          method="POST" action="/students/delete/${student.getId()}"--%>
<%--          enctype="multipart/form-data"--%>
<%--          onsubmit="return confirm('Do you really want to delete');"--%>
<%--    >--%>
<%--        <input class="btn btn-primary"--%>
<%--               type="submit" name="submit" value="Delete"--%>
<%--        />--%>
<%--    </form>--%>
    <h1 style="font-size: 25px; text-align: center;"> Succesfully deleted <span style="color: red"></span></h1>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
        integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
        crossorigin="anonymous"></script>
</body>
</html>
