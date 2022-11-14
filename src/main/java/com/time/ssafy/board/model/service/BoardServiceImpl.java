package com.time.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.time.ssafy.util.SizeConstant;
import com.time.ssafy.board.model.dto.BoardDto;
import com.time.ssafy.board.model.dto.BoardRoomDto;
import com.time.ssafy.board.model.dto.FileDto;
import com.time.ssafy.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardRoomDto> boardRoomList(Map<String, Integer> map) throws Exception {
		int pgNo = map.get("pgno");
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		map.put("start", start);
		map.put("listsize", SizeConstant.LIST_SIZE);
			
		return boardMapper.boardRoomList(map);
	}

	@Override
	public List<BoardRoomDto> roomFindList(Map<String, Object> map) throws SQLException {
		int pgNo = (Integer)map.get("pgno");
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		map.put("start", start);
		map.put("listsize", SizeConstant.LIST_SIZE);
		
		return boardMapper.roomFindList(map);
	}	
	
	@Override
	public void addRoom(BoardRoomDto boardRoomDto) throws Exception {
		boardMapper.addRoom(boardRoomDto);
		
	}

	@Override
	public void modifyRoom(BoardRoomDto boardRoomDto) throws Exception {
		boardMapper.modifyRoom(boardRoomDto);
	}

	@Override
	public void deleteRoom(int roomNo) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.deleteRoom(roomNo);
	}

	
	// ****************************************************************** //

	@Override
	public List<BoardDto> boardFindList(Map<String, Object> map) throws Exception {
		int pgNo = (Integer)map.get("pgno");
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		map.put("start", start);
		map.put("listsize", SizeConstant.LIST_SIZE);
		
		return boardMapper.boardFindList(map);
	}
	
	@Override
	public List<BoardDto> boardList(Map<String, Object> map) throws Exception {
		System.out.println(map.get("pgno"));
		int pgNo = Integer.parseInt((String) map.get("pgno"));
		
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		map.put("start", start);
		map.put("listsize", SizeConstant.LIST_SIZE);
	
		return boardMapper.boardList(map);
	}
	

	@Override
	public void addBoard(BoardDto boardDto) throws Exception {
		System.out.println("글입력 전 dto : " + boardDto);
		boardMapper.addBoard(boardDto);
		System.out.println("글입력 후 dto : " + boardDto);
		List<FileDto> fileInfos = boardDto.getFileDtos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			boardMapper.registerFile(boardDto);
		}
	}


	@Override
	public void modifyBoard(BoardDto boardDto) throws Exception {
		boardMapper.modifyBoard(boardDto);
	}

	@Override
	public void deleteBoard(int articleNo) throws Exception {
		boardMapper.deleteBoard(articleNo);	
	}

	@Override
	public BoardDto boardView(Map<String, Integer> map) throws Exception {
		return boardMapper.boardView(map);
	}




}
