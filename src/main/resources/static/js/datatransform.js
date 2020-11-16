$(function () {
    function addTab(data, i) {
        var tab = $("<div/>", {
            "class": "col-md-1"
        }).append(
            $("<span/>", {
                "class": "tab-show",
                "html": data.id[i]
            })
        );
        return tab;
    }

    function addTab1(data, i) {
        var tab1 = $("<div/>", {
            "class": "col-md-11",
            "align": "left",
        }).append(
            $("<span/>", {
                "class": "tab-show",
                "html": data.text[i]
            })
        );
        return tab1;

    }

    /* function addanwser() {*/
    /*
    */

    /* }*/
    function addTab2(data) {

        var tab2 = $("<div/>", {
            "class": "col-md-12",
            "style": "text-align:center"
        }).append(
            $("<div/>", {
                "class": "row",
            }).append("<input type='radio' name='answer'>")
        );

        return tab2;
    }

    function addgruop() {
        var an = ['A', 'B', 'C', 'D'];
        for (i of an) {
            console.log(i);
            var tab3 = $("<div/>", {
                "class": "col-md-12",
                "id": "group"
            }).append(
                $("<div/>", {
                    "class": "row"
                }).append(addTab2(i))
            );
        }
        console.log(tab3);
        return tab3;

    }

    $.ajax({
        url: "GetInformation.php",
        async: true,
        /*data:{
            sid:sid
        },*/
        dataType: "json",
        success: function (data) {
            /*$(".col-md-11>span").html(data.text);*/
            $.each(data.id, function (i, n) {
                $("#timu").append(addTab(data, i), addTab1(data, i), $("<div/>", {
                    "class": "col-md-12",
                    "id": "answer"
                }))
            });
            $.each(data.id, function (i, n) {
                console.log(n);
                $("#answer").append(addgruop())
            });
        }
    });
});