package com.time.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.time.ssafy.board.model.dto.BoardDto;
import com.time.ssafy.board.model.dto.BoardRoomDto;

public interface BoardService {
	List<BoardRoomDto> boardRoomList(Map<String, Integer> map) throws Exception;
	List<BoardRoomDto> roomFindList(Map<String, Object> map) throws SQLException;
	void modifyRoom(BoardRoomDto boardRoomDto) throws Exception;
	void deleteRoom(int roomNo) throws Exception;
	void addRoom(BoardRoomDto boardRoomDto) throws Exception;
	
	
	List<BoardDto> boardList(Map<String, Object> map) throws Exception;
	List<BoardDto> boardFindList(Map<String, Object> map) throws Exception;
	void addBoard(BoardDto boardDto) throws Exception;
	void modifyBoard(BoardDto boardDto) throws Exception;
	void deleteBoard(int articleNo) throws Exception;
	BoardDto boardView(Map<String, Integer> map) throws Exception;
}
