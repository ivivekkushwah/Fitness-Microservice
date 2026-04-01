package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repo.ActivityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final UserValidationService userValidationService;



    public ResponseEntity<ActivityResponse> trackActivity(ActivityRequest request) {
        Boolean isValidUser = userValidationService.validateUser(request.getUserId());
        if(!isValidUser){
            throw new RuntimeException("Invalid user ID :-"+request.getUserId());
        }
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();
        Activity savedActivity = activityRepo.save(activity);
        return mapToResponse(savedActivity);
    }

    private ResponseEntity<ActivityResponse> mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setStartTime(activity.getStartTime());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setDuration(activity.getDuration());
        response.setType(activity.getType());
        response.setUserId(activity.getUserId());
        response.setId(activity.getId());
        return ResponseEntity.ok(response);
    }
}
