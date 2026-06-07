package com.example.webtoon_archive.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebtoon is a Querydsl query type for Webtoon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoon extends EntityPathBase<Webtoon> {

    private static final long serialVersionUID = -1053642542L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWebtoon webtoon = new QWebtoon("webtoon");

    public final StringPath author = createString("author");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath platform = createString("platform");

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public final QUser user;

    public QWebtoon(String variable) {
        this(Webtoon.class, forVariable(variable), INITS);
    }

    public QWebtoon(Path<? extends Webtoon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWebtoon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWebtoon(PathMetadata metadata, PathInits inits) {
        this(Webtoon.class, metadata, inits);
    }

    public QWebtoon(Class<? extends Webtoon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

