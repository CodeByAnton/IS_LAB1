package com.bashkir777.api.controllers;

import com.bashkir777.api.dto.*;
import com.bashkir777.api.services.ChaptersService;
import com.bashkir777.api.services.CoordinatesService;
import com.bashkir777.api.services.OrdenService;
import com.bashkir777.api.services.SpaceMarineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/info")
@RequiredArgsConstructor
public class InfoController {

    private final SpaceMarineService spaceMarineService;
    private final CoordinatesService coordinatesService;
    private final ChaptersService chaptersService;
    private final OrdenService ordenService;

    @GetMapping("/chapters")
    public ResponseEntity<PaginatedChapterDTO> getAllChapters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(chaptersService.getAllChapters(page, size));
    }

    @GetMapping("/coordinates")
    public ResponseEntity<PaginatedCoordinatesDTO> getAllCoordinates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(coordinatesService.getPageOfCoordinates(page, size));
    }

    @GetMapping("/space-marines")
    public ResponseEntity<PaginatedSpaceMarineDTO> getAllSpaceMarines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(spaceMarineService.getAllSpaceMarines(page, size));
    }

    @GetMapping("/ordens/{ordenId}/space-marines")
    public ResponseEntity<PaginatedSpaceMarineDTO> getSpaceMarinesByOrdenId(
            @PathVariable Long ordenId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(spaceMarineService.getSpaceMarinesByOrdenId(ordenId, page, size));
    }

    @GetMapping("/space-marines/no-orden")
    public ResponseEntity<PaginatedSpaceMarineDTO> getSpaceMarinesByOrdenId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(spaceMarineService.getSpaceMarinesWithoutOrden(page, size));
    }

    @GetMapping("/ordens")
    public ResponseEntity<List<OrdenDTO>> getAllOrdens() {
        return ResponseEntity.ok(ordenService.getAllOrdens());
    }

    @GetMapping("/space-marines/{id}")
    public SpaceMarineDTO getSpaceMarineById(@PathVariable Integer id) throws RuntimeException {
        return spaceMarineService.getSpaceMarineById(id).toDTO();
    }

    @ExceptionHandler({RuntimeException.class})
    private ResponseEntity<OperationInfo> badCredentials(RuntimeException exception) {
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(OperationInfo.builder().success(false)
                        .message(exception.getMessage()).build());
    }

}
