var ajaxUrl = "ajax/admin/users/";
var datatableApi;

function switchUser() {
    alert("hi!");
    updateTable();
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({

        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    debugger;
    makeEditable();
    $("input[type='checkbox']:checked").change(function() {
        alert("hey! I'm checked!");
    });
    $("input[type='checkbox']").change(function() {
        alert("Wow!");
    });
    $(document).on('change', 'input[Id="checkbox"]', function (e) {
        alert($(this).val());
    });
});