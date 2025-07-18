package orochi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import orochi.model.DoctorNote;

import java.util.List;

/**
 * Repository interface for DoctorNote entity to handle database operations.
 */
@Repository
public interface DoctorNoteRepository extends JpaRepository<DoctorNote, Integer> {

    /**
     * Find all notes for a specific appointment with eager loading of doctor and user information.
     *
     * @param appointmentId The ID of the appointment
     * @return List of doctor notes for the appointment
     */
    @Query("SELECT n FROM DoctorNote n LEFT JOIN FETCH n.doctor d LEFT JOIN FETCH d.user WHERE n.appointmentId = :appointmentId ORDER BY n.createdAt DESC")
    List<DoctorNote> findByAppointmentIdOrderByCreatedAtDesc(@Param("appointmentId") Integer appointmentId);

    /**
     * Find all notes created by a specific doctor.
     *
     * @param doctorId The ID of the doctor
     * @return List of notes created by the doctor
     */
    List<DoctorNote> findByDoctorIdOrderByCreatedAtDesc(Integer doctorId);

    /**
     * Find notes for a specific appointment created by a specific doctor.
     *
     * @param appointmentId The ID of the appointment
     * @param doctorId The ID of the doctor
     * @return List of matching notes
     */
    List<DoctorNote> findByAppointmentIdAndDoctorIdOrderByCreatedAtDesc(Integer appointmentId, Integer doctorId);

    /**
     * Check if any notes exist for a specific appointment.
     *
     * @param appointmentId The ID of the appointment
     * @return true if notes exist, false otherwise
     */
    boolean existsByAppointmentId(Integer appointmentId);
}
