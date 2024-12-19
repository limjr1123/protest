package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService service;

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Integer bno) {

        List<CommentDto> list = null;

        try {
            list = service.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK); //200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST); //400
        }
    }


    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@RequestBody CommentDto dto, @PathVariable Integer cno) {
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setCno(cno);
        System.out.println("dto = " + dto);

        try {
            int cnt = service.modify(dto);
            if (cnt != 1)
                throw new Exception("Modify failed");

            return new ResponseEntity <>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity <>("MOD_FAIL", HttpStatus.BAD_REQUEST);
        }

    }

//     test용 데이터
//    {
//        "pcno":0,
//            "comment":"hi"
//    }


    @PostMapping("/comments") // /ch4/comments?bno=1419 POST
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session) {
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setBno(bno);
        System.out.println("dto = " + dto);

        try {
            int cnt = service.write(dto);
            if (cnt != 1)
                throw new Exception("Write failed");

            return new ResponseEntity <>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity <>("WRT_FAIL", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
//        String commenter = (String) session.getAttribute("id");
        String commenter = "asdf";

        try {
            int cnt = service.remove(cno,bno,commenter);
            if (cnt != 1)
                throw new Exception("Delete failed");

            return new ResponseEntity <>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity <>("DEL_FAIL", HttpStatus.BAD_REQUEST);
        }
    }
}
