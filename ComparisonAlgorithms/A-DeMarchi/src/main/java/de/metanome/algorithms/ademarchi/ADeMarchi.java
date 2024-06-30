//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.algorithms.ademarchi;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.result_receiver.InclusionDependencyResultReceiver;
import de.metanome.algorithm_integration.results.InclusionDependency;
import de.metanome.algorithms.ademarchi.config.Configuration;
import de.metanome.util.AttributeHelper;
import de.metanome.util.BitSetIterator;
import de.metanome.util.InclusionDependencyBuilder;
import de.metanome.util.Pair;
import de.metanome.util.TableInfo;
import de.metanome.util.TableInfoFactory;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ADeMarchi {
	protected InclusionDependencyResultReceiver resultReceiver = null;
	private TableInfoFactory tableInfoFactory;
	protected int inputRowLimit = -1;
	protected boolean processEmpty = true;
	private AttributeHelper attributeHelper;
	private Configuration configuration;
	private int attributeCount;
	protected int violationRate = 0;
	private Attribute[] attributeIndex;
	private int[][] jointNumCount;
	private boolean[][] hasRecieveResult;
	private List<InclusionDependency> result;

	public ADeMarchi() {
	}

	public void execute(Configuration configuration) throws AlgorithmExecutionException {
		this.configuration = configuration;
		this.tableInfoFactory = new TableInfoFactory();
		this.attributeHelper = new AttributeHelper();
		this.result = new ArrayList();
		List<TableInfo> tables = this.tableInfoFactory.create(configuration.getRelationalInputGenerators(), configuration.getTableInputGenerators());
		this.attributeCount = this.getTotalColumnCount(tables);
		this.jointNumCount = new int[this.attributeCount][this.attributeCount];
		this.hasRecieveResult = new boolean[this.attributeCount][this.attributeCount];
		this.attributeIndex = new Attribute[this.attributeCount];
		this.fillAttributeIndex(tables);
		if (this.onlyOneTypePresent(tables)) {
			this.mindFindIND(tables);
		} else {
			this.handleMultipleDomains();
		}

		Iterator var3 = this.result.iterator();

		while(var3.hasNext()) {
			InclusionDependency ind = (InclusionDependency)var3.next();
			System.out.println(ind.toString());
		}

		System.out.println("----result size :" + this.result.size() + " -----");
	}

	private boolean onlyOneTypePresent(Collection<TableInfo> tables) {
		return ((Set)tables.stream().flatMap((t) -> {
			return t.getColumnTypes().stream();
		}).collect(Collectors.toSet())).size() == 1;
	}

	private void fillAttributeIndex(Collection<TableInfo> tables) {
		int attributeId = 0;
		Iterator var3 = tables.iterator();

		while(var3.hasNext()) {
			TableInfo table = (TableInfo)var3.next();

			for(int index = 0; index < table.getColumnCount(); ++index) {
				this.attributeIndex[attributeId] = Attribute.builder().id(attributeId).tableName(table.getTableName()).name((String)table.getColumnNames().get(index)).type((String)table.getColumnTypes().get(index)).relationalInputGenerator(table.getRelationalInputGenerator()).tableInputGenerator(table.getTableInputGenerator()).build();
				++attributeId;
			}
		}

	}

	private void handleSingleDomain(List<TableInfo> tables) throws AlgorithmExecutionException {
		BitSet nonEmptyAttributes = new BitSet(this.attributeCount);
		Map<String, BitSet> attributesByValue = new HashMap();
		int attributeId = 0;
		Iterator var5 = tables.iterator();

		while(var5.hasNext()) {
			TableInfo table = (TableInfo)var5.next();

			try {
				RelationalInputGenerator generator = table.selectInputGenerator();
				Throwable var8 = null;

				try {
					RelationalInput input = generator.generateNewCopy();
					Throwable var10 = null;

					try {
						int read = 0;
						int limit = this.inputRowLimit;

						while(input.hasNext()) {
							++read;
							if (limit > 0 && read > limit) {
								break;
							}

							List<String> values = input.next();

							for(int index = 0; index < values.size(); ++index) {
								String value = (String)values.get(index);
								if (value != null) {
									nonEmptyAttributes.set(attributeId + index);
									((BitSet)attributesByValue.computeIfAbsent(value, (v) -> {
										return new BitSet(this.attributeCount);
									})).set(attributeId + index);
								}
							}
						}

						attributeId += input.numberOfColumns();
					} catch (Throwable var39) {
						var10 = var39;
						throw var39;
					} finally {
						if (input != null) {
							if (var10 != null) {
								try {
									input.close();
								} catch (Throwable var38) {
									var10.addSuppressed(var38);
								}
							} else {
								input.close();
							}
						}

					}
				} catch (Throwable var41) {
					var8 = var41;
					throw var41;
				} finally {
					if (generator != null) {
						if (var8 != null) {
							try {
								generator.close();
							} catch (Throwable var37) {
								var8.addSuppressed(var37);
							}
						} else {
							generator.close();
						}
					}

				}
			} catch (Exception var43) {
				throw new AlgorithmExecutionException("relation scan failed", var43);
			}
		}

		if (this.processEmpty) {
			nonEmptyAttributes.flip(0, this.attributeCount);
			BitSetIterator iterator = BitSetIterator.of(nonEmptyAttributes);
			BitSet allAttributes = new BitSet(this.attributeCount);
			allAttributes.set(0, this.attributeCount);
			System.out.println("------ if (this.processEmpty) -------");

			while(iterator.hasNext()) {
				this.handleEmptyAttribute(iterator.next(), allAttributes);
			}
		}

		this.computeInclusionDependencies(this.computeClosures(attributesByValue));
	}

	private void handleMultipleDomains() throws AlgorithmExecutionException {
		Map<String, BitSet> attributesByType = this.groupAttributesByType();
		Iterator var2 = attributesByType.values().iterator();

		while(var2.hasNext()) {
			BitSet attributes = (BitSet)var2.next();
			this.handleDomain(attributes);
		}

	}

	private Map<String, BitSet> groupAttributesByType() {
		Map<String, BitSet> attributesByType = new HashMap();
		Attribute[] var2 = this.attributeIndex;
		int var3 = var2.length;

		for(int var4 = 0; var4 < var3; ++var4) {
			Attribute attribute = var2[var4];
			((BitSet)attributesByType.computeIfAbsent(attribute.getType(), (k) -> {
				return new BitSet(this.attributeIndex.length);
			})).set(attribute.getId());
		}

		return attributesByType;
	}

	private int getTotalColumnCount(Collection<TableInfo> info) {
		return info.stream().mapToInt(TableInfo::getColumnCount).sum();
	}

	private void handleDomain(BitSet attributes) throws AlgorithmExecutionException {
		Map<String, BitSet> attributesByValue = this.groupAttributesByValue(attributes);
		BitSet[] closures = this.computeClosures(attributesByValue);
		this.computeInclusionDependencies(closures);
	}

	private Map<String, BitSet> groupAttributesByValue(BitSet attributes) throws AlgorithmExecutionException {
		Map<String, BitSet> attributesByValue = new HashMap();
		BitSet emptyAttributes = new BitSet(this.attributeIndex.length);
		BitSetIterator iterator = BitSetIterator.of(attributes);

		while(iterator.hasNext()) {
			this.addValues(iterator.next(), attributesByValue, emptyAttributes);
		}

		if (this.processEmpty) {
			BitSetIterator empty = BitSetIterator.of(emptyAttributes);

			while(empty.hasNext()) {
				this.handleEmptyAttribute(empty.next(), attributes);
			}
		}

		return attributesByValue;
	}

	private void handleEmptyAttribute(int attribute, BitSet attributes) throws AlgorithmExecutionException {
		BitSetIterator iterator = BitSetIterator.of(attributes);

		while(iterator.hasNext()) {
			int other = iterator.next();
			if (attribute != other) {
				this.receiveIND(this.attributeIndex[attribute], this.attributeIndex[other]);
			}
		}

	}

	private BitSet[] computeClosures(Map<String, BitSet> attributesByValue) {
		BitSet[] closures = new BitSet[this.attributeCount];

		for(int i = 0; i < this.attributeCount; ++i) {
			BitSet bitSet = new BitSet(this.attributeCount);
			bitSet.set(0, this.attributeCount, true);
			closures[i] = bitSet;
		}

		Iterator var3 = attributesByValue.entrySet().iterator();

		while(var3.hasNext()) {
			Map.Entry<String, BitSet> entry = (Map.Entry)var3.next();
			BitSetIterator iterator = BitSetIterator.of((BitSet)entry.getValue());

			while(iterator.hasNext()) {
				int attribute = iterator.next();
				closures[attribute].and((BitSet)entry.getValue());

				int var10002;
				int rightHideSide;
				for(BitSetIterator iter2 = BitSetIterator.of((BitSet)entry.getValue()); iter2.hasNext(); var10002 = this.jointNumCount[attribute][rightHideSide]++) {
					rightHideSide = iter2.next();
				}
			}
		}

		return closures;
	}

	private void computeInclusionDependencies(BitSet[] closures) throws AlgorithmExecutionException {
		for(int i = 0; i < this.attributeCount; ++i) {
			for(int j = 0; j < this.attributeCount; ++j) {
				if (j != i) {
					int violation = this.jointNumCount[i][i] - this.jointNumCount[i][j];
					double vioRate = (double)this.violationRate / 100.0;
					if ((double)violation <= vioRate * (double)this.jointNumCount[i][i]) {
						this.receiveIND(this.attributeIndex[i], this.attributeIndex[j]);
					}
				}
			}
		}

	}

	private void receiveIND(Attribute lhs, Attribute rhs) throws AlgorithmExecutionException {
		if (!this.hasRecieveResult[lhs.getId()][rhs.getId()]) {
			InclusionDependency ind = InclusionDependencyBuilder.dependent().column(lhs.getTableName(), lhs.getName()).referenced().column(rhs.getTableName(), rhs.getName()).build();
			this.result.add(ind);
			this.resultReceiver.receiveResult(ind);
			this.hasRecieveResult[lhs.getId()][rhs.getId()] = true;
		}
	}

	public void mindFindIND(List<TableInfo> tables) throws AlgorithmExecutionException {
		int attributeId = 0;
		Map<String, Set<Integer>> invertedIndex = new HashMap();

		RelationalInput input;
		int maxNum;
		int allCount;
		List pairList;
		//int i;
		for(Iterator var5 = tables.iterator(); var5.hasNext(); attributeId += input.numberOfColumns()) {
			TableInfo table = (TableInfo)var5.next();
			RelationalInputGenerator generator = table.selectInputGenerator();
			input = generator.generateNewCopy();
			maxNum = 0;
			allCount = this.inputRowLimit;

			while(input.hasNext()) {
				++maxNum;
				if (allCount > 0 && maxNum > allCount) {
					break;
				}

				pairList = input.next();

				for(int i = 0; i < pairList.size(); ++i) {
					String value = (String)pairList.get(i);
					if (value != null) {
						if (invertedIndex.keySet().contains(value)) {
							if (!((Set)invertedIndex.get(value)).contains(attributeId + i)) {
								((Set)invertedIndex.get(value)).add(attributeId + i);
							}
						} else {
							Set<Integer> l = new HashSet();
							l.add(attributeId + i);
							invertedIndex.put(value, l);
						}
					}
				}
			}
		}

		Map<Integer, List<Pair<Integer, Integer>>> nameToPart = new HashMap();

		for(int i = 0; i < this.attributeCount; ++i) {
			List<Pair<Integer, Integer>> pairList2 = new ArrayList();

			for(maxNum = 0; maxNum < this.attributeCount; ++maxNum) {
				Pair<Integer, Integer> pair = new Pair(maxNum, 0);
				pairList2.add(pair);
			}

			nameToPart.put(i, pairList2);
		}

		Iterator var18 = invertedIndex.values().iterator();

		while(var18.hasNext()) {
			Set<Integer> list = (Set)var18.next();
			Iterator var22 = list.iterator();

			while(var22.hasNext()) {
				Integer str = (Integer)var22.next();
				pairList = (List)nameToPart.get(str);

				for(int i = 0; i < pairList.size(); ++i) {
					Pair<Integer, Integer> pair = (Pair)pairList.get(i);
					Iterator var30 = list.iterator();

					while(var30.hasNext()) {
						Integer str1 = (Integer)var30.next();
						if (((Integer)pair.first).equals(str1)) {
							Pair<Integer, Integer> pair2 = new Pair(str1, (Integer)pair.second + 1);
							pairList.set(i, pair2);
						}
					}
				}
			}
		}

		for(int i = 0; i < this.attributeCount; ++i) {
			List<Pair<Integer, Integer>> pairList3 = (List)nameToPart.get(i);
			maxNum = 0;
			Iterator var25 = pairList3.iterator();

			while(var25.hasNext()) {
				Pair<Integer, Integer> pair = (Pair)var25.next();
				if ((Integer)pair.second > maxNum) {
					maxNum = (Integer)pair.second;
				}
			}

			allCount = maxNum - (int)((float)maxNum * ((float)this.violationRate / 100.0F));
			Iterator var27 = pairList3.iterator();

			while(var27.hasNext()) {
				Pair<Integer, Integer> pair = (Pair)var27.next();
				if ((Integer)pair.second >= allCount && !((Integer)pair.first).equals(i)) {
					this.receiveIND(this.attributeIndex[i], this.attributeIndex[(Integer)pair.first]);
				}
			}
		}

	}

	private void addValues(int attributeId, Map<String, BitSet> attributesByValue, BitSet emptyAttributes) throws AlgorithmExecutionException {
		Attribute attribute = this.attributeIndex[attributeId];
		boolean hasValue = this.attributeHelper.getValues(attribute.selectInputGenerator(), attribute.getTableName(), attribute.getName(), this.inputRowLimit, (value) -> {
			((BitSet)attributesByValue.computeIfAbsent(value, (v) -> {
				return new BitSet(this.attributeCount);
			})).set(attributeId);
		});
		if (!hasValue) {
			emptyAttributes.set(attributeId);
		}

	}
}
