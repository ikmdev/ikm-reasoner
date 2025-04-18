package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 - 2025 Integrated Knowledge Management
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullReleaseSnapshotTransformer {

	private static final Logger LOG = LoggerFactory.getLogger(FullReleaseSnapshotTransformer.class);

	// id effectiveTime ...

	public List<String> transformFile(Path file) throws Exception {
		HashMap<String, String> id_lines = new LinkedHashMap<>();
		List<String> lines = Files.readAllLines(file);
		lines.stream().skip(1).forEach(line -> {
			String[] fields = line.split("\\t");
			String id = fields[0];
			int effectiveTime = Integer.parseInt(fields[1]);
			String prior = id_lines.putIfAbsent(id, line);
			if (prior != null) {
				int priorEffectiveTime = Integer.parseInt(prior.split("\\t")[1]);
				if (effectiveTime > priorEffectiveTime)
					id_lines.put(id, line);
			}
		});
		ArrayList<String> ret = new ArrayList<>(id_lines.values());
		ret.addFirst(lines.getFirst());
		return ret;
	}

	public void transformDirectory(Path source_dir, Path target_dir) throws Exception {
		LOG.info("Source dir: " + source_dir.toAbsolutePath());
		LOG.info("Target dir: " + target_dir.toAbsolutePath());
		Files.createDirectories(target_dir);
		for (Path file : Files.list(source_dir).toList()) {
			if (!file.toString().contains("sct2_"))
				continue;
			LOG.info("File: " + file);
			Path target_file = file.getFileName();
			target_file = Paths.get(target_dir.toString(), target_file.toString().replace("Full", "Snapshot"));
			LOG.info("    : " + target_file);
			List<String> snapshot = transformFile(file);
			Files.write(target_file, snapshot);
		}
	}

}
