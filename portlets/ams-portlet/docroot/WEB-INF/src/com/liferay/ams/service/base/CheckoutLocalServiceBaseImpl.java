/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.service.base;

import com.liferay.ams.model.Checkout;
import com.liferay.ams.service.AssetLocalService;
import com.liferay.ams.service.CheckoutLocalService;
import com.liferay.ams.service.DefinitionLocalService;
import com.liferay.ams.service.TypeLocalService;
import com.liferay.ams.service.persistence.AssetPersistence;
import com.liferay.ams.service.persistence.CheckoutPersistence;
import com.liferay.ams.service.persistence.DefinitionPersistence;
import com.liferay.ams.service.persistence.TypePersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the checkout local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.ams.service.impl.CheckoutLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.ams.service.impl.CheckoutLocalServiceImpl
 * @see com.liferay.ams.service.CheckoutLocalServiceUtil
 * @generated
 */
public abstract class CheckoutLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements CheckoutLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.ams.service.CheckoutLocalServiceUtil} to access the checkout local service.
	 */

	/**
	 * Adds the checkout to the database. Also notifies the appropriate model listeners.
	 *
	 * @param checkout the checkout
	 * @return the checkout that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Checkout addCheckout(Checkout checkout) throws SystemException {
		checkout.setNew(true);

		return checkoutPersistence.update(checkout, false);
	}

	/**
	 * Creates a new checkout with the primary key. Does not add the checkout to the database.
	 *
	 * @param checkoutId the primary key for the new checkout
	 * @return the new checkout
	 */
	public Checkout createCheckout(long checkoutId) {
		return checkoutPersistence.create(checkoutId);
	}

	/**
	 * Deletes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param checkoutId the primary key of the checkout
	 * @return the checkout that was removed
	 * @throws PortalException if a checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public Checkout deleteCheckout(long checkoutId)
		throws PortalException, SystemException {
		return checkoutPersistence.remove(checkoutId);
	}

	/**
	 * Deletes the checkout from the database. Also notifies the appropriate model listeners.
	 *
	 * @param checkout the checkout
	 * @return the checkout that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public Checkout deleteCheckout(Checkout checkout) throws SystemException {
		return checkoutPersistence.remove(checkout);
	}

	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Checkout.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return checkoutPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return checkoutPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return checkoutPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return checkoutPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public Checkout fetchCheckout(long checkoutId) throws SystemException {
		return checkoutPersistence.fetchByPrimaryKey(checkoutId);
	}

	/**
	 * Returns the checkout with the primary key.
	 *
	 * @param checkoutId the primary key of the checkout
	 * @return the checkout
	 * @throws PortalException if a checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Checkout getCheckout(long checkoutId)
		throws PortalException, SystemException {
		return checkoutPersistence.findByPrimaryKey(checkoutId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return checkoutPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the checkouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of checkouts
	 * @param end the upper bound of the range of checkouts (not inclusive)
	 * @return the range of checkouts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Checkout> getCheckouts(int start, int end)
		throws SystemException {
		return checkoutPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of checkouts.
	 *
	 * @return the number of checkouts
	 * @throws SystemException if a system exception occurred
	 */
	public int getCheckoutsCount() throws SystemException {
		return checkoutPersistence.countAll();
	}

	/**
	 * Updates the checkout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param checkout the checkout
	 * @return the checkout that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Checkout updateCheckout(Checkout checkout) throws SystemException {
		return updateCheckout(checkout, true);
	}

	/**
	 * Updates the checkout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param checkout the checkout
	 * @param merge whether to merge the checkout with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the checkout that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Checkout updateCheckout(Checkout checkout, boolean merge)
		throws SystemException {
		checkout.setNew(false);

		return checkoutPersistence.update(checkout, merge);
	}

	/**
	 * Returns the asset local service.
	 *
	 * @return the asset local service
	 */
	public AssetLocalService getAssetLocalService() {
		return assetLocalService;
	}

	/**
	 * Sets the asset local service.
	 *
	 * @param assetLocalService the asset local service
	 */
	public void setAssetLocalService(AssetLocalService assetLocalService) {
		this.assetLocalService = assetLocalService;
	}

	/**
	 * Returns the asset persistence.
	 *
	 * @return the asset persistence
	 */
	public AssetPersistence getAssetPersistence() {
		return assetPersistence;
	}

	/**
	 * Sets the asset persistence.
	 *
	 * @param assetPersistence the asset persistence
	 */
	public void setAssetPersistence(AssetPersistence assetPersistence) {
		this.assetPersistence = assetPersistence;
	}

	/**
	 * Returns the checkout local service.
	 *
	 * @return the checkout local service
	 */
	public CheckoutLocalService getCheckoutLocalService() {
		return checkoutLocalService;
	}

	/**
	 * Sets the checkout local service.
	 *
	 * @param checkoutLocalService the checkout local service
	 */
	public void setCheckoutLocalService(
		CheckoutLocalService checkoutLocalService) {
		this.checkoutLocalService = checkoutLocalService;
	}

	/**
	 * Returns the checkout persistence.
	 *
	 * @return the checkout persistence
	 */
	public CheckoutPersistence getCheckoutPersistence() {
		return checkoutPersistence;
	}

	/**
	 * Sets the checkout persistence.
	 *
	 * @param checkoutPersistence the checkout persistence
	 */
	public void setCheckoutPersistence(CheckoutPersistence checkoutPersistence) {
		this.checkoutPersistence = checkoutPersistence;
	}

	/**
	 * Returns the definition local service.
	 *
	 * @return the definition local service
	 */
	public DefinitionLocalService getDefinitionLocalService() {
		return definitionLocalService;
	}

	/**
	 * Sets the definition local service.
	 *
	 * @param definitionLocalService the definition local service
	 */
	public void setDefinitionLocalService(
		DefinitionLocalService definitionLocalService) {
		this.definitionLocalService = definitionLocalService;
	}

	/**
	 * Returns the definition persistence.
	 *
	 * @return the definition persistence
	 */
	public DefinitionPersistence getDefinitionPersistence() {
		return definitionPersistence;
	}

	/**
	 * Sets the definition persistence.
	 *
	 * @param definitionPersistence the definition persistence
	 */
	public void setDefinitionPersistence(
		DefinitionPersistence definitionPersistence) {
		this.definitionPersistence = definitionPersistence;
	}

	/**
	 * Returns the type local service.
	 *
	 * @return the type local service
	 */
	public TypeLocalService getTypeLocalService() {
		return typeLocalService;
	}

	/**
	 * Sets the type local service.
	 *
	 * @param typeLocalService the type local service
	 */
	public void setTypeLocalService(TypeLocalService typeLocalService) {
		this.typeLocalService = typeLocalService;
	}

	/**
	 * Returns the type persistence.
	 *
	 * @return the type persistence
	 */
	public TypePersistence getTypePersistence() {
		return typePersistence;
	}

	/**
	 * Sets the type persistence.
	 *
	 * @param typePersistence the type persistence
	 */
	public void setTypePersistence(TypePersistence typePersistence) {
		this.typePersistence = typePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.ams.model.Checkout",
			checkoutLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.ams.model.Checkout");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return Checkout.class;
	}

	protected String getModelClassName() {
		return Checkout.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = checkoutPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = AssetLocalService.class)
	protected AssetLocalService assetLocalService;
	@BeanReference(type = AssetPersistence.class)
	protected AssetPersistence assetPersistence;
	@BeanReference(type = CheckoutLocalService.class)
	protected CheckoutLocalService checkoutLocalService;
	@BeanReference(type = CheckoutPersistence.class)
	protected CheckoutPersistence checkoutPersistence;
	@BeanReference(type = DefinitionLocalService.class)
	protected DefinitionLocalService definitionLocalService;
	@BeanReference(type = DefinitionPersistence.class)
	protected DefinitionPersistence definitionPersistence;
	@BeanReference(type = TypeLocalService.class)
	protected TypeLocalService typeLocalService;
	@BeanReference(type = TypePersistence.class)
	protected TypePersistence typePersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private CheckoutLocalServiceClpInvoker _clpInvoker = new CheckoutLocalServiceClpInvoker();
}