package com.time.ssafy.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.time.ssafy.board.model.dto.BoardDto;
import com.time.ssafy.board.model.dto.BoardRoomDto;
import com.time.ssafy.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardDto> boardList(int roomNo) throws Exception {
		return boardMapper.boardList(roomNo);
	}
	
	@Override
	public List<BoardRoomDto> boardRoomList() throws Exception {
		return boardMapper.boardRoomList();
	}

	@Override
	public void addBoard(BoardDto boardDto) throws Exception {
		boardMapper.addBoard(boardDto);
		
	}

	@Override
	public void addRoom(BoardRoomDto boardRoomDto) throws Exception {
		boardMapper.addRoom(boardRoomDto);
		
	}

	@Override
	public void modifyBoard(BoardDto boardDto) throws Exception {
		boardMapper.modifyBoard(boardDto);
	}

	@Override
	public void deleteBoard(int boardNo) throws Exception {
		boardMapper.deleteBoard(boardNo);	
	}

	@Override
	public BoardDto boardView(Map<String, Integer> map) throws Exception {
		return boardMapper.boardView(map);
	}



}
