<#import "partials/main.ftlh" as main />
<@main.renderWith scripts=["/js/comment.js","https://code.jquery.com/jquery-3.4.1.slim.min.js","https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"] styles=["/css/main.css","https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css","https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"]>
    <#if theme??>
        <div class="row">
            <div class="col-5"><h2>Chat name: ${theme.name}</h2></div>
            <div class="col-4">Chat created by: ${theme.user.login}, ${theme.ldt}</div>
        </div>
        <div class="row">
            <div class="col-12">
                <#if user??>
                    <p style="font-size: 2em"><button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal">Add new comment</button></p>
                </#if>
            </div>
        </div>
        <input type="hidden" id="themeId" value="${theme.id}">
        <div id="comments">

        </div>
    </#if>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-uppercase" id="exampleModalLabel">Create new comment</h5>
                    <button type="button" class="close" data-dismiss="modal1" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="form-comment" >
                        <div class="form-group">
                            <label for="Name2">Comment text</label>
                            <input minlength="5" maxlength="100" name="text" type="text" class="form-control" id="text" aria-describedby="emailHelp" placeholder="Enter comment text">
                        </div>
                        <button type="button" onclick="addComment()" class="btn btn-primary">Add</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').trigger('focus')
        })
    </script>

</@main.renderWith>
