package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.dto.ExaminationDTO;

public interface ExaminationService {
	List<ExaminationDTO> findAllTermsByDay(Long pharmacistId, Date start);
	List<Long> countTermsByDays( Long pharmacistId, Date start, int num);
	List<Long> countTermsByMonths(Long pharmacistId, Date start);
}
