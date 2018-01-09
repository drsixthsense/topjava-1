var ajaxUrl = "ajax/admin/users/";
var datatableApi;

function updateEnabled(elem) {
    var enabled = elem.is(":checked");
    var userRow = elem.closest("tr");
    var id = elem.getValue();
    var vals = [id, enabled];
    $.ajax({
        type: "PUT",
        url: ajaxUrl,
        data: vals.serialize(),
        success: function () {
            updateTable();
            successNoty("Action performed");
        }
    });
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
    $('input[name="enabled"]').change(function () {
        updateEnabled($(this));
    });
});