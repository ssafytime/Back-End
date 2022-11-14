package com.time.ssafy.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.time.ssafy.board.model.dto.BoardDto;
import com.time.ssafy.board.model.dto.BoardRoomDto;
import com.time.ssafy.board.model.dto.FileDto;
import com.time.ssafy.board.model.service.BoardService;
import com.time.ssafy.board.model.service.BoardServiceImpl;

@RestController()
@RequestMapping("/rooms")
public class BoardController {
	
	@Autowired
	private ServletContext servletContext;

	private BoardService boardService;

	@Autowired
	public BoardController(BoardServiceImpl boardService) {
		this.boardService = boardService;
	}

	@GetMapping()
	public ResponseEntity<?> boardRoomList(@RequestBody Map<String, Integer> map) {
		try {
			System.out.println(map);
			List<BoardRoomDto> list = boardService.boardRoomList(map); // >>>> JSON Array
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

	@GetMapping("/{key}/{word}")
	public ResponseEntity<?> roomFindList(@PathVariable String key, @PathVariable String word,
			@RequestBody Map<String, Object> map) {
		try {
			map.put("key", key);
			map.put("word", word);

			System.out.println(map);

			List<BoardRoomDto> list = boardService.roomFindList(map); // >>>> JSON Array
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
			System.out.println(boardRoomDto);
			Map<String, Integer> map = new HashMap<>();
			map.put("pgno", 1);
			boardService.addRoom(boardRoomDto);
			List<BoardRoomDto> list = boardService.boardRoomList(map); // >>>> JSON Array
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
	//
	@PutMapping("/{roomno}")
	public ResponseEntity<?> modifyRoom(@PathVariable("roomno") int roomNo,@RequestBody BoardRoomDto boardRoomDto) {
		try {
			boardRoomDto.setRoomNo(roomNo);
			System.out.println(boardRoomDto);


			boardService.modifyRoom(boardRoomDto);

			Map<String, Integer> map = new HashMap<>();
			map.put("pgno", 1);

			List<BoardRoomDto> list = boardService.boardRoomList(map); // >>>> JSON Array
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

	@DeleteMapping("/{roomno}")
	public ResponseEntity<?> deleteRoom(@PathVariable("roomno") int roomNo) {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("pgno", 1);
			boardService.deleteRoom(roomNo);
			List<BoardRoomDto> list = boardService.boardRoomList(map); // >>>> JSON Array
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


	// *********************************************************************************************** //

	@GetMapping("/{roomno}/boards/{key}/{word}")
	public ResponseEntity<?> boardFindList(@PathVariable String roomno, @PathVariable String key, @PathVariable String word,
			@RequestBody Map<String, Object> map) {
		try {
			map.put("roomNo", roomno);
			map.put("key", key);
			map.put("word", word);

			System.out.println(map);


			List<BoardDto> list = boardService.boardFindList(map); // >>>> JSON Array
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
	//	
	//	
	//	@GetMapping("/{roomno}/boards")
	//	public ResponseEntity<?> boardList(@RequestBody Map<String,Integer> map) {
	//		try {
	//			List<BoardDto> list = boardService.boardList(map); // >>>> JSON Array
	//			if(list != null && !list.isEmpty()) {
	//				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	//			} else {
	//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	//			}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return exceptionHandling(e);
	//		}
	//	}
	//	
	@PostMapping("/{roomno}/boards")
	public ResponseEntity<?> addBoard(@PathVariable("roomno") int roomNo, 
			BoardDto boardDto,  @RequestParam("files") MultipartFile[] files, @RequestParam Map<String, Object> map) {
		try {
			
			boardDto.setRoomNo(roomNo);

//			FileUpload 관련 설정.
			if (!files[0].isEmpty()) {
				String realPath = servletContext.getRealPath("/upload");
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = realPath + File.separator + today;
				System.out.println("저장 폴더 : {} "+ saveFolder);
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				List<FileDto> fileInfos = new ArrayList<FileDto>();
				for (MultipartFile mfile : files) {
					FileDto fileInfoDto = new FileDto();
					String originalFileName = mfile.getOriginalFilename();
					if (!originalFileName.isEmpty()) {
						String saveFileName = UUID.randomUUID().toString()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setSaveFolder(today);
						fileInfoDto.setOriginalFile(originalFileName);
						fileInfoDto.setSaveFile(saveFileName);
						System.out.println("원본 파일 이름 : {}, 실제 저장 파일 이름 : {} "+ mfile.getOriginalFilename() +" " + saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					fileInfos.add(fileInfoDto);
				}
				boardDto.setFileDtos(fileInfos);
			}
			boardService.addBoard(boardDto);

			map.put("roomNo", roomNo);

			List<BoardDto> list = boardService.boardList(map); // >>>> JSON Array
			
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
	//	
	//	@PutMapping("/{roomno}/boards")
	//	public ResponseEntity<?> modifyBoard(@PathVariable("roomno") int roomNo, 
	//			@RequestBody BoardDto boardDto, @RequestBody Map<String, Integer> map) {
	//		try {
	//			boardDto.setRoomNo(roomNo);
	//			boardService.modifyBoard(boardDto);
	//			
	//			map.put("roomNo", roomNo);
	//			
	//			List<BoardDto> list = boardService.boardList(map); // >>>> JSON Array
	//			if(list != null && !list.isEmpty()) {
	//				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	//			} else {
	//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	//			}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return exceptionHandling(e);
	//		}
	//	}
	//	
	//	@DeleteMapping("/{roomno}/boards/{articleno}")
	//	public ResponseEntity<?> deleteBoard(@PathVariable("roomno") int roomNo, 
	//			@PathVariable("articleno") int articleNo, @RequestBody Map<String, Integer> map) {
	//		try {
	//			boardService.deleteBoard(articleNo);
	//			map.put("roomNo", roomNo);
	//			
	//			List<BoardDto> list = boardService.boardList(map); // >>>> JSON Array
	//			if(list != null && !list.isEmpty()) {
	//				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
	//			} else {
	//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	//			}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return exceptionHandling(e);
	//		}
	//	}
	//	
	//	@GetMapping("/{roomno}/boards/{articleno}")
	//	public ResponseEntity<?> boardView(@RequestBody Map<String, Integer> map) {
	//		try {
	//			BoardDto boardDto =	boardService.boardView(map);
	//
	//			if(boardDto != null) {
	//				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
	//			} else {
	//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	//			}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return exceptionHandling(e);
	//		}
	//	}
	//	

	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
