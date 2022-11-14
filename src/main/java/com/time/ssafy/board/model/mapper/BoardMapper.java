package com.time.ssafy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.time.ssafy.board.model.dto.BoardDto;
import com.time.ssafy.board.model.dto.BoardRoomDto;

@Mapper
public interface BoardMapper {
	// room
	List<BoardRoomDto> boardRoomList(Map<String, Integer> map) throws SQLException;
	List<BoardRoomDto> roomFindList(Map<String, Object> map) throws SQLException;	
	void modifyRoom(BoardRoomDto boardRoomDto) throws SQLException;
	void deleteRoom(int roomNo) throws SQLException;
	void addRoom(BoardRoomDto boardRoomDto) throws SQLException;
	
	// board
	List<BoardDto> boardFindList(Map<String, Object> map) throws SQLException;
	List<BoardDto> boardList(Map<String, Object> map) throws SQLException;
	void addBoard(BoardDto boardDto) throws SQLException;
	void modifyBoard(BoardDto boardDto) throws SQLException;
	void deleteBoard(int articleNo) throws SQLException;
	BoardDto boardView(Map<String, Integer> map) throws SQLException;
	void registerFile(BoardDto boardDto) throws Exception;
}
