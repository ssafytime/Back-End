package com.time.ssafy.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.time.ssafy.board.model.dto.BoardDto;
import com.time.ssafy.board.model.dto.BoardRoomDto;
import com.time.ssafy.board.model.service.BoardService;
import com.time.ssafy.board.model.service.BoardServiceImpl;

@RestController("/rooms")
public class BoardController {
	
	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardServiceImpl boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping
	public ResponseEntity<?> boardRoomList() {
		try {
			List<BoardRoomDto> list = boardService.boardRoomList(); // >>>> JSON Array
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardRoomDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addRoom(@RequestBody BoardRoomDto boardRoomDto) {
		try {
			boardService.addRoom(boardRoomDto);
			List<BoardRoomDto> list = boardService.boardRoomList(); // >>>> JSON Array
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardRoomDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	@GetMapping("/{roomno}/boards")
	public ResponseEntity<?> boardList(@PathVariable("roomno") int roomNo) {
		try {
			List<BoardDto> list = boardService.boardList(roomNo); // >>>> JSON Array
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/{roomno}/boards")
	public ResponseEntity<?> addBoard(@PathVariable("roomno") int roomNo, @RequestBody BoardDto boardDto) {
		try {
			boardDto.setRoomNo(roomNo);
			boardService.addBoard(boardDto);
			List<BoardDto> list = boardService.boardList(roomNo); // >>>> JSON Array
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/{roomno}/boards")
	public ResponseEntity<?> userModify(@PathVariable("roomno") int roomNo, @RequestBody BoardDto boardDto) {
		try {
			boardDto.setRoomNo(roomNo);
			boardService.modifyBoard(boardDto);
			List<BoardDto> list = boardService.boardList(roomNo); // >>>> JSON Array
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/{roomno}/boards/{boardno}")
	public ResponseEntity<?> boardView(@RequestBody Map<String, Integer> map) {
		try {
			BoardDto boardDto =	boardService.boardView(map);

			if(boardDto != null) {
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	@DeleteMapping("/{roomno}/boards/{boardno}")
	public ResponseEntity<?> userDelete(@PathVariable("roomno") int roomNo, @PathVariable("boardno") int boardNo) {
		try {
			boardService.deleteBoard(boardNo);
			List<BoardDto> list = boardService.boardList(roomNo); // >>>> JSON Array
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
