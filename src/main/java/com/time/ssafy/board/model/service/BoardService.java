package com.time.ssafy.board.model.service;

import java.util.List;
import java.util.Map;

import com.time.ssafy.board.model.dto.BoardDto;
import com.time.ssafy.board.model.dto.BoardRoomDto;

public interface BoardService {
	
	List<BoardDto> boardList(int roomNo) throws Exception;
	List<BoardRoomDto> boardRoomList() throws Exception;
	void addBoard(BoardDto boardDto) throws Exception;
	void addRoom(BoardRoomDto boardRoomDto) throws Exception;
	void modifyBoard(BoardDto boardDto) throws Exception;
	void deleteBoard(int boardNo) throws Exception;
	BoardDto boardView(Map<String, Integer> map) throws Exception;
}
