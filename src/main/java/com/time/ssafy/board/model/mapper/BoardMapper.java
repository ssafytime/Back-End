package com.time.ssafy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.time.ssafy.board.model.dto.BoardDto;
import com.time.ssafy.board.model.dto.BoardRoomDto;

@Mapper
public interface BoardMapper {

	List<BoardDto> boardList(int roomNo) throws SQLException;
	List<BoardRoomDto> boardRoomList() throws SQLException;
	void addBoard(BoardDto boardDto) throws SQLException;
	void addRoom(BoardRoomDto boardRoomDto) throws SQLException;
	void modifyBoard(BoardDto boardDto) throws SQLException;
	void deleteBoard(int boardNo) throws SQLException;
	BoardDto boardView(Map<String, Integer> map) throws SQLException;
	
}
