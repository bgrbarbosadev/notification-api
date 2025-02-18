package br.com.bgrbarbosa.notification_api.controller;


import br.com.bgrbarbosa.notification_api.controller.mapper.NotificationMapper;
import br.com.bgrbarbosa.notification_api.model.Notification;
import br.com.bgrbarbosa.notification_api.model.dto.NotificationDTO;
import br.com.bgrbarbosa.notification_api.service.exception.ResourceNotFound;
import br.com.bgrbarbosa.notification_api.service.impl.NotificationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Notification", description = "Contem as operações de Cadastro, Atualização, Buscas e Deleção de notificações de agendamento")
public class NotificationController {

    private final NotificationServiceImpl service;

    private final NotificationMapper mapper;

    @PostMapping
    @Operation(summary = "Cria uma nova notificação", description = "Recurso para criar uma nova notificação a ser enviada",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDTO.class)))
            })
    public ResponseEntity<NotificationDTO> insert(@RequestBody @Valid NotificationDTO notification){
        Notification result = service.insertNotification(mapper.parseToEntity(notification));
        return ResponseEntity.ok(mapper.parseToDto(result));
    }

    @GetMapping
    @Operation(summary = "Listar todas as notificações", description = "Listar todas as notificações cadastradas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista todas as notificações cadastradas",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = NotificationDTO.class))))
            })
    public ResponseEntity<List<NotificationDTO>> search(){
        List<NotificationDTO> listDTO = mapper.parseToListDTO(service.findAllNotification());
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperar uma notificação pelo id", description = "Recuperar uma notificação pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Notificação recuperada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFound.class)))
            })
    public ResponseEntity<NotificationDTO> searchById(@PathVariable("id") Long id){

        Notification result = service.searchNotificationById(id);
        return ResponseEntity.ok(mapper.parseToDto(result));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma notificacao pelo id", description = "Deletar uma notificação pelo ID",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Notificação deletada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFound.class)))
            })
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        service.deleteNotification(id);
        return ResponseEntity.accepted().body("Successfully deleted!!");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Cancela notificação", description = "Cancela notificação",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Notificação Cancelada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFound.class)))
            })
    public ResponseEntity<NotificationDTO> cancel(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(mapper.parseToDto(service.cancelNotification(id)));
    }

}
