/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="KaleoNotificationRecipientPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationRecipientPersistence
 * @see       KaleoNotificationRecipientUtil
 * @generated
 */
public class KaleoNotificationRecipientPersistenceImpl
	extends BasePersistenceImpl<KaleoNotificationRecipient>
	implements KaleoNotificationRecipientPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoNotificationRecipientImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEONOTIFICATIONID = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoNotificationId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEONOTIFICATIONID = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoNotificationId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		EntityCacheUtil.putResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			kaleoNotificationRecipient.getPrimaryKey(),
			kaleoNotificationRecipient);
	}

	public void cacheResult(
		List<KaleoNotificationRecipient> kaleoNotificationRecipients) {
		for (KaleoNotificationRecipient kaleoNotificationRecipient : kaleoNotificationRecipients) {
			if (EntityCacheUtil.getResult(
						KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNotificationRecipientImpl.class,
						kaleoNotificationRecipient.getPrimaryKey(), this) == null) {
				cacheResult(kaleoNotificationRecipient);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(KaleoNotificationRecipientImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoNotificationRecipientImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		EntityCacheUtil.removeResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			kaleoNotificationRecipient.getPrimaryKey());
	}

	public KaleoNotificationRecipient create(long kaleoNotificationRecipientId) {
		KaleoNotificationRecipient kaleoNotificationRecipient = new KaleoNotificationRecipientImpl();

		kaleoNotificationRecipient.setNew(true);
		kaleoNotificationRecipient.setPrimaryKey(kaleoNotificationRecipientId);

		return kaleoNotificationRecipient;
	}

	public KaleoNotificationRecipient remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoNotificationRecipient remove(long kaleoNotificationRecipientId)
		throws NoSuchNotificationRecipientException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoNotificationRecipient kaleoNotificationRecipient = (KaleoNotificationRecipient)session.get(KaleoNotificationRecipientImpl.class,
					new Long(kaleoNotificationRecipientId));

			if (kaleoNotificationRecipient == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoNotificationRecipientId);
				}

				throw new NoSuchNotificationRecipientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoNotificationRecipientId);
			}

			return remove(kaleoNotificationRecipient);
		}
		catch (NoSuchNotificationRecipientException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoNotificationRecipient removeImpl(
		KaleoNotificationRecipient kaleoNotificationRecipient)
		throws SystemException {
		kaleoNotificationRecipient = toUnwrappedModel(kaleoNotificationRecipient);

		Session session = null;

		try {
			session = openSession();

			if (kaleoNotificationRecipient.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoNotificationRecipientImpl.class,
						kaleoNotificationRecipient.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoNotificationRecipient);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			kaleoNotificationRecipient.getPrimaryKey());

		return kaleoNotificationRecipient;
	}

	public KaleoNotificationRecipient updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient,
		boolean merge) throws SystemException {
		kaleoNotificationRecipient = toUnwrappedModel(kaleoNotificationRecipient);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoNotificationRecipient, merge);

			kaleoNotificationRecipient.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			kaleoNotificationRecipient.getPrimaryKey(),
			kaleoNotificationRecipient);

		return kaleoNotificationRecipient;
	}

	protected KaleoNotificationRecipient toUnwrappedModel(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		if (kaleoNotificationRecipient instanceof KaleoNotificationRecipientImpl) {
			return kaleoNotificationRecipient;
		}

		KaleoNotificationRecipientImpl kaleoNotificationRecipientImpl = new KaleoNotificationRecipientImpl();

		kaleoNotificationRecipientImpl.setNew(kaleoNotificationRecipient.isNew());
		kaleoNotificationRecipientImpl.setPrimaryKey(kaleoNotificationRecipient.getPrimaryKey());

		kaleoNotificationRecipientImpl.setKaleoNotificationRecipientId(kaleoNotificationRecipient.getKaleoNotificationRecipientId());
		kaleoNotificationRecipientImpl.setGroupId(kaleoNotificationRecipient.getGroupId());
		kaleoNotificationRecipientImpl.setCompanyId(kaleoNotificationRecipient.getCompanyId());
		kaleoNotificationRecipientImpl.setUserId(kaleoNotificationRecipient.getUserId());
		kaleoNotificationRecipientImpl.setUserName(kaleoNotificationRecipient.getUserName());
		kaleoNotificationRecipientImpl.setCreateDate(kaleoNotificationRecipient.getCreateDate());
		kaleoNotificationRecipientImpl.setModifiedDate(kaleoNotificationRecipient.getModifiedDate());
		kaleoNotificationRecipientImpl.setKaleoDefinitionId(kaleoNotificationRecipient.getKaleoDefinitionId());
		kaleoNotificationRecipientImpl.setKaleoNotificationId(kaleoNotificationRecipient.getKaleoNotificationId());
		kaleoNotificationRecipientImpl.setRecipientClassName(kaleoNotificationRecipient.getRecipientClassName());
		kaleoNotificationRecipientImpl.setRecipientClassPK(kaleoNotificationRecipient.getRecipientClassPK());
		kaleoNotificationRecipientImpl.setRecipientRoleType(kaleoNotificationRecipient.getRecipientRoleType());
		kaleoNotificationRecipientImpl.setAddress(kaleoNotificationRecipient.getAddress());

		return kaleoNotificationRecipientImpl;
	}

	public KaleoNotificationRecipient findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoNotificationRecipient findByPrimaryKey(
		long kaleoNotificationRecipientId)
		throws NoSuchNotificationRecipientException, SystemException {
		KaleoNotificationRecipient kaleoNotificationRecipient = fetchByPrimaryKey(kaleoNotificationRecipientId);

		if (kaleoNotificationRecipient == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoNotificationRecipientId);
			}

			throw new NoSuchNotificationRecipientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoNotificationRecipientId);
		}

		return kaleoNotificationRecipient;
	}

	public KaleoNotificationRecipient fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoNotificationRecipient fetchByPrimaryKey(
		long kaleoNotificationRecipientId) throws SystemException {
		KaleoNotificationRecipient kaleoNotificationRecipient = (KaleoNotificationRecipient)EntityCacheUtil.getResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
				KaleoNotificationRecipientImpl.class,
				kaleoNotificationRecipientId, this);

		if (kaleoNotificationRecipient == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoNotificationRecipient = (KaleoNotificationRecipient)session.get(KaleoNotificationRecipientImpl.class,
						new Long(kaleoNotificationRecipientId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoNotificationRecipient != null) {
					cacheResult(kaleoNotificationRecipient);
				}

				closeSession(session);
			}
		}

		return kaleoNotificationRecipient;
	}

	public List<KaleoNotificationRecipient> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public List<KaleoNotificationRecipient> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	public List<KaleoNotificationRecipient> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoDefinitionId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNotificationRecipient> list = (List<KaleoNotificationRecipient>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

				query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoNotificationRecipient>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoNotificationRecipient findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchNotificationRecipientException, SystemException {
		List<KaleoNotificationRecipient> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationRecipientException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoNotificationRecipient findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchNotificationRecipientException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoNotificationRecipient> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationRecipientException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoNotificationRecipient[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNotificationRecipientId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationRecipientException, SystemException {
		KaleoNotificationRecipient kaleoNotificationRecipient = findByPrimaryKey(kaleoNotificationRecipientId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotificationRecipient[] array = new KaleoNotificationRecipientImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoNotificationRecipient, kaleoDefinitionId,
					orderByComparator, true);

			array[1] = kaleoNotificationRecipient;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoNotificationRecipient, kaleoDefinitionId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoNotificationRecipient getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoNotificationRecipient kaleoNotificationRecipient,
		long kaleoDefinitionId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

		query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoNotificationRecipient);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNotificationRecipient> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId) throws SystemException {
		return findByKaleoNotificationId(kaleoNotificationId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId, int start, int end) throws SystemException {
		return findByKaleoNotificationId(kaleoNotificationId, start, end, null);
	}

	public List<KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(kaleoNotificationId),
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNotificationRecipient> list = (List<KaleoNotificationRecipient>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEONOTIFICATIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

				query.append(_FINDER_COLUMN_KALEONOTIFICATIONID_KALEONOTIFICATIONID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNotificationId);

				list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoNotificationRecipient>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEONOTIFICATIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public KaleoNotificationRecipient findByKaleoNotificationId_First(
		long kaleoNotificationId, OrderByComparator orderByComparator)
		throws NoSuchNotificationRecipientException, SystemException {
		List<KaleoNotificationRecipient> list = findByKaleoNotificationId(kaleoNotificationId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNotificationId=");
			msg.append(kaleoNotificationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationRecipientException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoNotificationRecipient findByKaleoNotificationId_Last(
		long kaleoNotificationId, OrderByComparator orderByComparator)
		throws NoSuchNotificationRecipientException, SystemException {
		int count = countByKaleoNotificationId(kaleoNotificationId);

		List<KaleoNotificationRecipient> list = findByKaleoNotificationId(kaleoNotificationId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNotificationId=");
			msg.append(kaleoNotificationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationRecipientException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KaleoNotificationRecipient[] findByKaleoNotificationId_PrevAndNext(
		long kaleoNotificationRecipientId, long kaleoNotificationId,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationRecipientException, SystemException {
		KaleoNotificationRecipient kaleoNotificationRecipient = findByPrimaryKey(kaleoNotificationRecipientId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotificationRecipient[] array = new KaleoNotificationRecipientImpl[3];

			array[0] = getByKaleoNotificationId_PrevAndNext(session,
					kaleoNotificationRecipient, kaleoNotificationId,
					orderByComparator, true);

			array[1] = kaleoNotificationRecipient;

			array[2] = getByKaleoNotificationId_PrevAndNext(session,
					kaleoNotificationRecipient, kaleoNotificationId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoNotificationRecipient getByKaleoNotificationId_PrevAndNext(
		Session session, KaleoNotificationRecipient kaleoNotificationRecipient,
		long kaleoNotificationId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

		query.append(_FINDER_COLUMN_KALEONOTIFICATIONID_KALEONOTIFICATIONID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoNotificationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoNotificationRecipient);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNotificationRecipient> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<KaleoNotificationRecipient> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoNotificationRecipient> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoNotificationRecipient> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNotificationRecipient> list = (List<KaleoNotificationRecipient>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_KALEONOTIFICATIONRECIPIENT.concat(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoNotificationRecipient>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoNotificationRecipient kaleoNotificationRecipient : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			remove(kaleoNotificationRecipient);
		}
	}

	public void removeByKaleoNotificationId(long kaleoNotificationId)
		throws SystemException {
		for (KaleoNotificationRecipient kaleoNotificationRecipient : findByKaleoNotificationId(
				kaleoNotificationId)) {
			remove(kaleoNotificationRecipient);
		}
	}

	public void removeAll() throws SystemException {
		for (KaleoNotificationRecipient kaleoNotificationRecipient : findAll()) {
			remove(kaleoNotificationRecipient);
		}
	}

	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoDefinitionId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEONOTIFICATIONRECIPIENT_WHERE);

				query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByKaleoNotificationId(long kaleoNotificationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(kaleoNotificationId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEONOTIFICATIONID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEONOTIFICATIONRECIPIENT_WHERE);

				query.append(_FINDER_COLUMN_KALEONOTIFICATIONID_KALEONOTIFICATIONID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNotificationId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEONOTIFICATIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEONOTIFICATIONRECIPIENT);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoNotificationRecipient>> listenersList = new ArrayList<ModelListener<KaleoNotificationRecipient>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoNotificationRecipient>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
	@BeanReference(type = KaleoDefinitionPersistence.class)
	protected KaleoDefinitionPersistence kaleoDefinitionPersistence;
	@BeanReference(type = KaleoInstancePersistence.class)
	protected KaleoInstancePersistence kaleoInstancePersistence;
	@BeanReference(type = KaleoInstanceTokenPersistence.class)
	protected KaleoInstanceTokenPersistence kaleoInstanceTokenPersistence;
	@BeanReference(type = KaleoLogPersistence.class)
	protected KaleoLogPersistence kaleoLogPersistence;
	@BeanReference(type = KaleoNodePersistence.class)
	protected KaleoNodePersistence kaleoNodePersistence;
	@BeanReference(type = KaleoNotificationPersistence.class)
	protected KaleoNotificationPersistence kaleoNotificationPersistence;
	@BeanReference(type = KaleoNotificationRecipientPersistence.class)
	protected KaleoNotificationRecipientPersistence kaleoNotificationRecipientPersistence;
	@BeanReference(type = KaleoTaskPersistence.class)
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(type = KaleoTaskAssignmentPersistence.class)
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(type = KaleoTaskAssignmentInstancePersistence.class)
	protected KaleoTaskAssignmentInstancePersistence kaleoTaskAssignmentInstancePersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEONOTIFICATIONRECIPIENT = "SELECT kaleoNotificationRecipient FROM KaleoNotificationRecipient kaleoNotificationRecipient";
	private static final String _SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE = "SELECT kaleoNotificationRecipient FROM KaleoNotificationRecipient kaleoNotificationRecipient WHERE ";
	private static final String _SQL_COUNT_KALEONOTIFICATIONRECIPIENT = "SELECT COUNT(kaleoNotificationRecipient) FROM KaleoNotificationRecipient kaleoNotificationRecipient";
	private static final String _SQL_COUNT_KALEONOTIFICATIONRECIPIENT_WHERE = "SELECT COUNT(kaleoNotificationRecipient) FROM KaleoNotificationRecipient kaleoNotificationRecipient WHERE ";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoNotificationRecipient.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEONOTIFICATIONID_KALEONOTIFICATIONID_2 =
		"kaleoNotificationRecipient.kaleoNotificationId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoNotificationRecipient.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoNotificationRecipient exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoNotificationRecipient exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoNotificationRecipientPersistenceImpl.class);
}