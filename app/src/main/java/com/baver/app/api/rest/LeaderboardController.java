package com.baver.app.api.rest;

import com.baver.app.api.rest.dto.GlobalLeaderboardResponse;
import com.baver.app.application.leaderboard.GetGlobalLeaderboardUseCase;
import com.baver.app.common.constants.ApiVersion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaderboards")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Leaderboard Management", description = "Operations related to leaderboards")
public class LeaderboardController {

    private final GetGlobalLeaderboardUseCase getGlobalLeaderboardUseCase;

    @Operation(summary = "Get Global Leaderboard", description = "Retrieves the global leaderboard")
    @GetMapping(headers = ApiVersion.Constants.V1_MATCH, path = "/global")
    public ResponseEntity<GlobalLeaderboardResponse> getGlobalLeaderboardV1(@RequestParam(defaultValue = "10") @Min(1) @Max(100) int limit ) {
        log.info("REQUEST_RECEIVED endpoint=GetGlobalLeaderboard version=1 topN={}", limit);
        GlobalLeaderboardResponse response = getGlobalLeaderboardUseCase.getGlobalLeaderboard(limit);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
