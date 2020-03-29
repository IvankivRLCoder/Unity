//package com.example.converter;
//
//import com.example.dto.MainUserTaskDto;
//import com.example.model.Task;
//import com.example.model.User;
//import com.example.model.UserTask;
//import org.modelmapper.Converter;
//import org.modelmapper.spi.MappingContext;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//public class UserTaskListConverter implements Converter<Set<MainUserTaskDto>, Set<UserTask>> {
//
//    @Override
//    public Set<UserTask> convert(MappingContext<Set<MainUserTaskDto>, Set<UserTask>> context) {
//        return context.getSource()
//                .stream()
//                .map(ut ->
//                        UserTask
//                                .builder()
//                                .participationDate(LocalDate.parse(ut.getParticipationDate()))
//                                .comment(ut.getComment())
//                                .approved(ut.isApproved())
//                                .isCreator(ut.isCreator())
//                                .user((User) context.getParent().getDestination())
//                                .build()
//                ).collect(Collectors.toSet());
//    }
//
//}
