var main = {

    init: function () {

        console.log(this);
        var _this = this;

        //등록
        $('#btn-save').on('click', function () {
            _this.save();
        });

        //수정
        $('#btn-update').on('click',function(){
           _this.update();
        });

        //삭제
        $('#btn-delete').on('click',function () {
            _this.delete();
        })

    },

    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: "POST"
            , url: "/api/v1/posts"
            , dataType: 'json'
            , contentType: 'application/json; charset=utf-8'
            , data: JSON.stringify(data)
        }).done(function () {
            alert("글이 등록되었습니다");
            window.location.href = '/';
        }).fail(function () {
            alert(JSON.stringify(onerror));
        });

    },

    update: function(){

        var id = $('#id').val();
        var data = {
            "title" : $("#title").val()
            ,"content" : $("#content").val()
        };
        console.log(`${data.title}`);

        $.ajax({
            method:"PUT"
            ,url :"/api/v1/posts/"+id
            ,dataType : "json"
            ,contentType: "application/json; charset=utf-8"
            ,data : JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },

    delete:function(){
        var id = $("#id").val();

        //삭제
        $.ajax({
            url: '/api/v1/posts/' +id
            ,method: "delete"
            ,dataType: "JSON"
            ,data :{
                "id" :  id
            }
        }).done(function () {
                alert("글이 삭제되었습니다.");
                window.location.href="/";
        }).fail(function () {
                alert(JSON.stringify(onerror));
        });
    }
};

main.init();