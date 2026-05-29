package com.example.webtoon_archive.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWebtoon is a Querydsl query type for Webtoon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoon extends EntityPathBase<Webtoon> {

    private static final long serialVersionUID = -1053642542L;

    public static final QWebtoon webtoon = new QWebtoon("webtoon");

    public final StringPath author = createString("author");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath platform = createString("platform");

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public QWebtoon(String variable) {
        super(Webtoon.class, forVariable(variable));
    }

    public QWebtoon(Path<? extends Webtoon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWebtoon(PathMetadata metadata) {
        super(Webtoon.class, metadata);
    }

}

