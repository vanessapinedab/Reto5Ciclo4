function closeForm() {
    $(".form-popup-bg").removeClass("is-visible");
}

function openForm(id) {
    $(".form-popup-bg").addClass("is-visible");

    $("#id_pro").val(id);
}

function openStock(id) {
    $(".form2-popup-bg").addClass("is-visible");
    $("#id_pro").val(id);
}

function openFormAddO() {
    $(".form1-popup-bg").addClass("is-visible");
    var userName = sessionStorage.getItem("userName");
    var fecha = new Date();
    $("#fechaRegistroNuevoPedido").val(fecha);
    $("#asesor1").val(userName);
    $("#save");
    getProducts();
    $("#detallePedido2")
}


$(document).ready(function($) {
    /* Contact Form Interactions */
    $("#btnOpenForm").on("click", function(event) {
        event.preventDefault();

        $(".form-popup-bg").addClass("is-visible");
    });

    //close popup when clicking x or off popup

    $(".form-popup-bg").on("click", function(event) {
        if (
            $(event.target).is(".form-popup-bg") ||
            $(event.target).is("#btnCloseForm") ||
            $(event.target).is(".form2-popup-bg")
        ) {
            event.preventDefault();
            $(this).removeClass("is-visible");
        }
    });

    //para el segundo formulario llamado form1
    /* Contact Form Interactions */
    $("#btnOpenForm").on("click", function(event) {
        event.preventDefault();

        $(".form1-popup-bg").addClass("is-visible");
    });

    //close popup when clicking x or off popup
    $(".form1-popup-bg").on("click", function(event) {
        if (
            $(event.target).is(".form1-popup-bg") ||
            $(event.target).is("#btnCloseForm")
        ) {
            event.preventDefault();
            $(this).removeClass("is-visible");
        }
    });

    //para el tercer formulario llamado form2
    /* Validación del Stock */
    $("#btnOpenForm").on("click", function(event) {
        event.preventDefault();

        $(".form2-popup-bg").addClass("is-visible");
    });

    //close popup when clicking x or off popup
    $(".form2-popup-bg").on("click", function(event) {
        if (
            $(event.target).is(".form2-popup-bg") ||
            $(event.target).is("#btnCloseForm")
        ) {
            event.preventDefault();
            $(this).removeClass("is-visible");
        }
    });
});


$("document").ready(function() {
    var userName = sessionStorage.getItem("userName");
    $("#usuario2").html(userName);
    var userEmail = sessionStorage.getItem("userEmail");
    $("#userEmail").html(userEmail);
    var userIdentification = sessionStorage.getItem("userIdentification");
    $("#userIdentification").html(userIdentification);
    var zonaLogin = sessionStorage.getItem("zonaLogin");
    $("#zonaLogin").html(zonaLogin);

    var userId = sessionStorage.getItem("userId");
    $("#userId").html(userId);


    getOrders(userId);

    console.log(userId)
});

//Consultar ordenes de pedido existentes
function getOrders(userId) {

    $.ajax({
        url: "http://localhost:8080/api/order/salesman/" + userId,
        type: "GET",
        datatype: "JSON",
        contentType: "application/json",
        success: function(response) {
            console.log(response);
            showOrders(response);
            return response;
            var xx = response;
        },
        error: function(jqXHR, textStatus, errorThrown) {},
    });
}

//Componer y mostrar la tabla con la consulta de las ordenes de pedidos existentes
function showOrders(response) {
    let table = "<table>";
    for (i = 0; i < response.length; i++) {
        //console.log(response[i].id);

        let regDayFormatted = new Date(response[i].registerDay).toLocaleDateString();

        table += "<tr>";
        table += "<td>" + response[i].id + "</td>";
        table += "<td>" + regDayFormatted + "</td>";
        table += "<td>" + response[i].status + "</td>";
        table += "<td>" + response[i].salesMan.name + "</td>";

        table += '<td><center><button onclick="getOrderById(' + response[i].id + ')">Ver Detalle</button><center></td>';
        table += '<td><center><button onclick="deleteProduct(' + response[i].id + ')">Borrar</button><center></td>';
        table += "</tr>";

    }

    table += "</table>";
    $("#cloneList tbody").html(table);
}

/*Obtener y mostrar en el formulario de actualización los datos del producto a actualizar*/
function getOrderById(id) {
    //jQuery.support.cors = true;
    $.ajax({
        dataType: 'json',
        url: "http://localhost:8080/api/order/" + id,
        type: 'GET',
        success: function(response) {
            console.log(response);
            // alert(response.registerDay);
            let order = response;
            $("#numeroPedido").val(order.id);
            $("#fechaRegistro").val(order.registerDay);
            $("#estadoPedido").val(order.status);
            $("#asesor").val(order.salesMan.name);
            //alert(order.products[1].brand)
            openForm(id);
            showOrdersDetail(response);

            console.log(Object.keys(response.products).length);

        }
    })
}


//Componer y mostrar la tabla con la consulta del detalle de las ordenes de pedidos seleccionadas
function showOrdersDetail(dato) {
    // $("estadoPedido").value("Actualizar Pedido");
    let table = "<center><table width=90% border='1' cellspacing>"
    table += "<tr><td colspan=12 ><center><strong>Listado de Pedido</strong></center></td></tr>";
    table += "<tr>";
    table += "<td><center>Id</center></td>";
    table += "<td><center>Marca</center></td>";
    table += "<td><center>Descripcion</center></td>";
    table += "<td><center>Cantidad Orden</center></td>";
    table += "<td><center>Inv. Stock</center></td>";
    table += "<td><center>Precio Unitario</center></td>";
    table += "</tr>";
    // alert(response.length);
    var tamano = Object.keys(dato.products).length;
    sw = 1;
    for (k = 1; k <= tamano; k++) {
        // for (k = 1; k < response.products.length; k++) {

        //console.log(response[i].id);
        if (sw == 1) {
            table += "<tr class = 'f1'>";
            sw = 0;
        } else {
            table += "<tr class = 'f2'>";
            sw = 1;
        }

        let priceFormatted = new Intl.NumberFormat("es-ES").format(dato.products[k].price);

        table += "<td>" + dato.products[k].id + "</td>";
        table += "<td>" + dato.products[k].brand + "</td>";
        table += "<td>" + dato.products[k].description + "</td>";
        table += "<td>" + dato.quantities[k] + "</td>";
        table += "<td>" + dato.products[k].quantity + "</td>";
        table += "<td>" + priceFormatted + "</td>";

        //table += "</tr>";

    }
    // alert(dato.products[1].brand);
    table += "</table></center>";
    $("#detallePedido").html(table);
}


/*Visualizar el Stock de Productos en el formulario del producto Seleccionado*/

function getProducts() {
    $.ajax({
        dataType: 'json',
        url: "http://localhost:8080/api/clone/all",
        type: 'GET',
        success: function(response) {
            console.log(response);
            // openStock(id);
            showProductsStock(response);
            console.log(Object.keys(response).length);

        }
    })
}



//Componer y mostrar la tabla con la consulta del Stock de los productos seleccionadas

function showProductsStock(dato) {
    //console.log(dato + "showProductsStock");
    let table = "<center><table width=90% border='1' cellspacing>"
    table += "<tr><td colspan=9 ><center><strong>Listado de Pedido</strong></center></td></tr>";
    table += "<tr>";
    table += "<td><center id='idProdAPedir'>Id</center></td>";
    table += "<td><center>Marca</center></td>";
    table += "<td><center>Procesador</center></td>";
    table += "<td><center>Memoria</center></td>";
    table += "<td><center>Disco Duro</center></td>";
    table += "<td><center>Disponibilidad</center></td>";
    table += "<td><center>Precio</center></td>";
    table += "<td><center>Inventario</center></td>";
    table += "<td><center>Cantidad a Pedir</center></td>";
    table += "</tr>";
    //console.log(dato);
    //var tamano2 = Object.keys(dato).length;
    //alert(tamano2);
    sw = 1;
    for (k = 0; k < dato.length; k++) {
        if (sw == 1) {
            table += "<tr class = 'f1'>";
            sw = 0;
        } else {
            table += "<tr class = 'f2'>";
            sw = 1;
        }

        table += "<td>" + dato[k].id + "</td>";
        table += "<td>" + dato[k].brand + "</td>";
        table += "<td>" + dato[k].procesor + "</td>";
        table += "<td>" + dato[k].memory + "</td>";
        table += "<td>" + dato[k].hardDrive + "</td>";
        table += "<td>" + dato[k].availability + "</td>";
        table += "<td>" + dato[k].price + "</td>";
        table += "<td>" + dato[k].quantity + "</td>";
        table += "<td><input class ='f1' id='cantidadAPedir' type = 'number' placeholder='ingrese cantidad'></input></td>";
        table += "</tr>";

    }
    table += "</table>";
    $("#detallePedido2").html(table);
}



//Ventana popup para actualizar productos existentes
function getJSONUpdate(id) {
    return {
        id: $.trim($("#numeroPedido1").val()),
        brand: $.trim($("#marca-producto").val()),
        procesor: $.trim($("#procesador-producto").val()),
        os: $.trim($("#so-producto").val()),
        description: $.trim($("#descripcion-producto").val()),
        memory: $.trim($("#memoria-producto").val()),
        hardDrive: $.trim($("#hdd-producto").val()),
        availability: $.trim($("#stock-producto").val()),
        price: $("#precio-producto").val(),
        quantity: $("#cantidad-producto").val(),
        photography: $.trim($("#foto-producto").val())
    };
}

$("#btn_post_update").click(function() {
    let data = autorizarPUT();
    let url = "http://localhost:8080/api/order/update";
    alert("La orden de pedido ha cambiado su estado a  " + $("#estadoPedido").val());
    // PETICION PUT (UPDATE)
    $.when(jqueryPUT(url, data)).done(function() { // AJAX => ASINCRONO

        closeForm();

    });
});

function jqueryPUT(url, data) {
    return $.ajax({
        url: url,
        type: "PUT",
        datatype: "JSON",
        data: JSON.stringify(data),
        contentType: "application/json"
    });
}

function autorizarPUT() {
    let data = {
        id: $("#numeroPedido").val(),
        status: $("#estadoPedido").val()
    }
    return data;
    /* $.ajax({
         url: url,
         type: "PUT",
         datatype: "JSON",
         data: JSON.stringify(data),
         contentType: "application/json" 
     }); */
}


$("#btn_post_create").click(function() {
    alert(userId)
    console.log(userId);

    /* let data = getJSONCreate();
     let url = "http://localhost:8080/api/order/new";
     getProducts();
     getOrders(userId);
     

     // PETICION POST (create)
     $.when(jqueryPOST(url, data)).done(function() { // AJAX => ASINCRONO
         alert("Se ha agregado correctamente!");
         closeForm();

     }); */
});

function jqueryPOST(url, data) {
    return $.ajax({
        url: url,
        type: "POST",
        datatype: "JSON",
        data: JSON.stringify(data),
        contentType: "application/json"
    });
}
/*

//Eliminar un producto del sistema
function deleteProduct(id) {

    let product = {
        id: id
    };

    console.log(product);
    let dataToSend = JSON.stringify(product);

    $.ajax({
        url: "http://localhost:8080/api/clone/" + id,
        type: "DELETE",
        datatype: "JSON",
        data: dataToSend,
        contentType: "application/json",
        success: function(response) {
            console.log(response);
            $("#productList").empty();
            getProducts();
            console.log("Se ha eliminado el registro del producto.");
            alert("Se ha eliminado el registro del producto.")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("No se ha eliminado el registro del producto, por favor verifique.");
            alert("Se ha eliminado el registro del producto, por favor verifique.")
        }
    });
}
*/


//Ventana popup para agregar nuevos productos
/*
    function getJSONCreate() {
if ($("#cantidadAPedir") > 0){
       x = $("#idProdAPedir").val();


}



        if ($("#marca1-producto").val().length == 0 ||
            $("#procesador1-producto").val().length == 0 ||
            $("#so1-producto").val().length == 0 ||
            $("#descripcion1-producto").val().length == 0 ||
            $("#memoria1-producto").val().length == 0 ||
            $("#hdd1-producto").val().length == 0 ||
            $("#stock1-producto").val().length == 0 ||
            $("#precio1-producto").val().length == 0 ||
            $("#cantidad1-producto").val().length == 0) {
            alert("Por favor llene todos los campos de registro solicitados.");
        } else {
            let prodData1 = {
                //id: $("#id1-producto").val(),
                brand: $.trim($("#marca1-producto").val()),
                procesor: $.trim($("#procesador1-producto").val()),
                os: $.trim($("#so1-producto").val()),
                description: $.trim($("#descripcion1-producto").val()),
                memory: $.trim($("#memoria1-producto").val()),
                hardDrive: $.trim($("#hdd1-producto").val()),
                availability: $.trim($("#stock1-producto").val()),
                price: $("#precio1-producto").val(),
                quantity: $("#cantidad1-producto").val(),
                photography: $.trim($("#foto1-producto").val()),
            }
            return prodData1;
        }
    }
*/