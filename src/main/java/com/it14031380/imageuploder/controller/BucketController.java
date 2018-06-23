package com.it14031380.imageuploder.controller;

import com.it14031380.imageuploder.services.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/")
public class BucketController {
    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        String imageUrl = this.amazonClient.uploadFile(file);
       // return new ModelAndView("redirect:" + imageUrl);
        return responceBody(imageUrl);

    }

    private String responceBody(String url){
        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>Image Uploader</title>\n" +
                "    <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css\">\n" +
                "    <script defer src=\"https://use.fontawesome.com/releases/v5.0.7/js/all.js\"></script>\n" +
                "    <script src=\"https://code.jquery.com/jquery-3.3.1.min.js\"\n" +
                "            integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\" crossorigin=\"anonymous\"></script>\n" +
                "    <script>\n" +
                "\n" +
                "\n" +
                "    window.onload = function() {\n" +
                "        var uploadedImageUrl = \"" +
                url+
                "\";\n" +
                "        var ip = \"http://18.218.141.77:8080\";\n" +
                "         swal({\n" +
                "             title: \"Good job!\",\n" +
                "             text: \"Image Sucessfully Uploaded.\",\n" +
                "             icon: \"success\",\n" +
                "\n" +
                "             buttons: [true, \"Open Image\"],\n" +
                "         })\n" +
                "             .then((willOpen) => {\n" +
                "                 if (willOpen) {\n" +
                "                     window.open(uploadedImageUrl);\n" +
                "                     window.location.href = ip;\n" +
                "                 }else{\n" +
                "                     window.location.href = ip;\n" +
                "                 }\n" +
                "             });\n" +
                "\n" +
                "     };\n" +
                "\n" +
                "        function readURL(input) {\n" +
                "            if (input.files && input.files[0]) {\n" +
                "                var reader = new FileReader();\n" +
                "                reader.onload = function (e) {\n" +
                "                    $('#blah')\n" +
                "                        .attr('src', e.target.result);\n" +
                "                };\n" +
                "                reader.readAsDataURL(input.files[0]);\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        $(document).ready(function () {\n" +
                "            $(\"#subtn\").click(function () {\n" +
                "                if ($('#filename').get(0).files.length === 0) {\n" +
                "                    swal(\"Oops!\", \"Please select an image to upload\", \"error\");\n" +
                "                    return false;\n" +
                "                } else {\n" +
                "                    $( \"#uploadForm\" ).submit();\n" +
                "                    swal(\"Uploading...\", {\n" +
                "                        button: false,\n" +
                "                      //  timer: 5000,\n" +
                "                    })\n" +
                "                }\n" +
                "            });\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "    <style>\n" +
                "        @import url('https://fonts.googleapis.com/css?family=Quicksand');\n" +
                "        @import url('https://fonts.googleapis.com/css?family=Raleway');\n" +
                "\n" +
                "        h1 {\n" +
                "            font-family: 'Quicksand', sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            font-family: 'Raleway', serif;\n" +
                "        }\n" +
                "\n" +
                "        .title {\n" +
                "            font-size: 3.5em;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "\n" +
                "            padding: 5px;\n" +
                "\n" +
                "            max-height: 50%;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        .column {\n" +
                "            padding: 4%;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        .col-left {\n" +
                "            padding: 0 auto;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<section class=\"hero is-primary is-fullheight\">\n" +
                "    <div class=\"hero-body\">\n" +
                "        <div class=\"container\">\n" +
                "            <div class=\"columns col-left\">\n" +
                "                <div class=\" column\">\n" +
                "                    <h1 class=\"title\">\n" +
                "                        Image Uploader\n" +
                "                    </h1>\n" +
                "                    <form  id=\"uploadForm\" method=\"post\" enctype=\"multipart/form-data\"\n" +
                "                          th:action=\"@{/uploadFile}\" >\n" +
                "                        <div class=\"field\">\n" +
                "                            <div class=\"file is-warning\">\n" +
                "                                <label class=\"file-label\">\n" +
                "                                    <input id=\"filename\" accept='image/*' onchange=\"readURL(this);\" class=\"file-input\"\n" +
                "                                           type=\"file\" name=\"file\">\n" +
                "                                    <span class=\"file-cta\">\n" +
                "                                <span class=\"file-icon\">\n" +
                "                                  <i class=\"fas fa-camera\"></i>\n" +
                "                                </span>\n" +
                "                                <span class=\"file-label\">\n" +
                "                                  Select Image…\n" +
                "                                </span>\n" +
                "                              </span>\n" +
                "                                </label>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"content\">\n" +
                "                            <br><br>\n" +
                "                            <p>\n" +
                "                                AWS Practical Assignment <br>\n" +
                "                                Current Trends of Software Engineering – SE4010 <br>\n" +
                "                                Student No : IT14031380 <br>\n" +
                "                                Student Name : Perera P.D.S <br>\n" +
                "                            </p>\n" +
                "                        </div>\n" +
                "                        <input id=\"subtn\" class=\"button button is-dark\" type=\"submit\" value=\"Upload\">\n" +
                "                    </form>\n" +
                "                </div>\n" +
                "                <br><br>\n" +
                "                <div class=\"column \">\n" +
                "                    <figure class=\" image\">\n" +
                "                        <img id=\"blah\" alt=\"your image\"\n" +
                "                             src=\"data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDU4IDU4IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1OCA1ODsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSI1MTJweCIgaGVpZ2h0PSI1MTJweCI+CjxnPgoJPHBhdGggZD0iTTU3LDZIMUMwLjQ0OCw2LDAsNi40NDcsMCw3djQ0YzAsMC41NTMsMC40NDgsMSwxLDFoNTZjMC41NTIsMCwxLTAuNDQ3LDEtMVY3QzU4LDYuNDQ3LDU3LjU1Miw2LDU3LDZ6IE01Niw1MEgyVjhoNTRWNTB6IiBmaWxsPSIjZWNmMGYxIi8+Cgk8cGF0aCBkPSJNMTYsMjguMTM4YzMuMDcxLDAsNS41NjktMi40OTgsNS41NjktNS41NjhDMjEuNTY5LDE5LjQ5OCwxOS4wNzEsMTcsMTYsMTdzLTUuNTY5LDIuNDk4LTUuNTY5LDUuNTY5ICAgQzEwLjQzMSwyNS42NCwxMi45MjksMjguMTM4LDE2LDI4LjEzOHogTTE2LDE5YzEuOTY4LDAsMy41NjksMS42MDIsMy41NjksMy41NjlTMTcuOTY4LDI2LjEzOCwxNiwyNi4xMzhzLTMuNTY5LTEuNjAxLTMuNTY5LTMuNTY4ICAgUzE0LjAzMiwxOSwxNiwxOXoiIGZpbGw9IiNlY2YwZjEiLz4KCTxwYXRoIGQ9Ik03LDQ2YzAuMjM0LDAsMC40Ny0wLjA4MiwwLjY2LTAuMjQ5bDE2LjMxMy0xNC4zNjJsMTAuMzAyLDEwLjMwMWMwLjM5MSwwLjM5MSwxLjAyMywwLjM5MSwxLjQxNCwwczAuMzkxLTEuMDIzLDAtMS40MTQgICBsLTQuODA3LTQuODA3bDkuMTgxLTEwLjA1NGwxMS4yNjEsMTAuMzIzYzAuNDA3LDAuMzczLDEuMDQsMC4zNDUsMS40MTMtMC4wNjJjMC4zNzMtMC40MDcsMC4zNDYtMS4wNC0wLjA2Mi0xLjQxM2wtMTItMTEgICBjLTAuMTk2LTAuMTc5LTAuNDU3LTAuMjY4LTAuNzItMC4yNjJjLTAuMjY1LDAuMDEyLTAuNTE1LDAuMTI5LTAuNjk0LDAuMzI1bC05Ljc5NCwxMC43MjdsLTQuNzQzLTQuNzQzICAgYy0wLjM3NC0wLjM3My0wLjk3Mi0wLjM5Mi0xLjM2OC0wLjA0NEw2LjMzOSw0NC4yNDljLTAuNDE1LDAuMzY1LTAuNDU1LDAuOTk3LTAuMDksMS40MTJDNi40NDcsNDUuODg2LDYuNzIzLDQ2LDcsNDZ6IiBmaWxsPSIjZWNmMGYxIi8+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPGc+CjwvZz4KPC9zdmc+Cg==\">\n" +
                "                    </figure>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>";
        return  body;
    }


}
