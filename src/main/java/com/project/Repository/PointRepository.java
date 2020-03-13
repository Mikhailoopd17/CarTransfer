package com.project.Repository;

import com.project.Entity.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<Point, Long> {
}
