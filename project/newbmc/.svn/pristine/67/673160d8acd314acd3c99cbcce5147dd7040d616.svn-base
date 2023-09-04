package egovframework.portal.common;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransDefinition {

	/**
	 * Default TransactionDefinition PROPAGATION_REQUIRED
	 *
	 * @return def
	 */
	public static DefaultTransactionDefinition getRequired() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return def;
	}

	/**
	 * Default TransactionDefinition PROPAGATION_REQUIRES_NEW
	 *
	 * @return def
	 */
	public static DefaultTransactionDefinition getRequiresNew() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		return def;
	}

}
