package dev.ikm.elk.snomed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SnomedRoles {

	public static long root = SnomedOwlOntology.root;

	public static class Role {

		public long destinationId;
		public long relationshipGroup;
		public long typeId;

		public Role(long destinationId, long relationshipGroup, long typeId) {
			super();
			this.destinationId = destinationId;
			this.relationshipGroup = relationshipGroup;
			this.typeId = typeId;
		}

		@Override
		public String toString() {
			return "[" + relationshipGroup + "] " + typeId + " -> " + destinationId;
		}

	}

	private HashMap<Long, Set<Role>> roles = new HashMap<>();;

	public static SnomedRoles init(Path file) throws IOException {
		SnomedRoles ret = new SnomedRoles();
		ret.load(file);
		return ret;
	}

	public void load(Path file) throws IOException {
		// id effectiveTime active moduleId sourceId destinationId relationshipGroup
		// typeId characteristicTypeId modifierId
		Files.lines(file).skip(1).map(line -> line.split("\\t")) //
				.filter(fields -> Integer.parseInt(fields[2]) == 1) // active
				.filter(fields -> Long.parseLong(fields[7]) != 116680003) // typeId
				.forEach(fields -> {
					long con = Long.parseLong(fields[4]); // sourceId
					long destination = Long.parseLong(fields[5]); // destinationId
					long relationshipGroup = Long.parseLong(fields[6]); // relationshipGroup
					long typeId = Long.parseLong(fields[7]); // typeId
					roles.computeIfAbsent(con, x -> new HashSet<>());
					roles.get(con).add(new Role(destination, relationshipGroup, typeId));
					// 900000000000011006 |Inferred relationship (core metadata concept)|
					if (Long.parseLong(fields[8]) != 900000000000011006l)
						throw new RuntimeException(fields[8]);
				});
	}

	public Set<Role> getRoles(long con) {
		return roles.get(con);
	}

}
